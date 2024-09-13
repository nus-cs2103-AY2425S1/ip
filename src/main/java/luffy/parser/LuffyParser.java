package luffy.parser;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import luffy.command.AddCommand;
import luffy.command.Command;
import luffy.command.DeleteCommand;
import luffy.command.ExitCommand;
import luffy.command.FindCommand;
import luffy.command.InvalidCommand;
import luffy.command.ListCommand;
import luffy.command.MarkCommand;
import luffy.command.UnmarkCommand;
import luffy.exception.LuffyException;
import luffy.storage.Storage;
import luffy.task.Deadline;
import luffy.task.Event;
import luffy.task.TaskList;
import luffy.task.Todo;
import luffy.ui.LuffyUI;

/**
 * Represents a command parser that ensures user commands
 * are of valid expressions
 */
public class LuffyParser {

    private final Scanner scanner;

    public LuffyParser(Scanner scanner) {
        this.scanner = scanner;
    }
    /**
     * Returns a Command object that contains the corresponding action
     * to be executed by the chatbot
     *
     * @param fullCommand the command inputted by the user retrieved from the scanner
     */
    public static Command parse(String fullCommand) {

        String[] commandDetails = fullCommand.split(" ", 2);
        assert(commandDetails.length >= 1);
        String commandWord = commandDetails[0];

        switch (commandWord) {
        case "todo":
            if (commandDetails.length == 1) {
                break;
            }
            return new AddCommand(new Todo(commandDetails[1], false));

        case "deadline":
            if (commandDetails.length == 1) {
                break;
            }
            Deadline deadlineTask = null;
            String[] taskAndDeadline = fullCommand.substring(9).split("/", 2);

            try {
                checkValidArguments(taskAndDeadline, 2);
            } catch (LuffyException e) {
                System.out.println(e.getMessage());
                break;
            }

            if (taskAndDeadline[1].startsWith("by") || taskAndDeadline[1].startsWith("By")) {
                String dateAndTime = taskAndDeadline[1].substring(3).trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime timeObj = LocalDateTime.parse(dateAndTime, formatter);
                String dateTime = timeObj.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
                deadlineTask = new Deadline(taskAndDeadline[0].trim(), dateTime + "HRS", false);
                return new AddCommand(deadlineTask);
            } else {
                deadlineTask = new Deadline(taskAndDeadline[0].trim(), taskAndDeadline[1].trim(), false);
                return new AddCommand(deadlineTask);
            }

        case "event":
            if (commandDetails.length == 1) {
                break;
            }
            String[] eventDetails = fullCommand.substring(6).split("/");
            try {
                checkValidArguments(eventDetails, 3);
            } catch (LuffyException e) {
                System.out.println(e.getMessage());
                break;
            }
            Event eventTask = new Event(eventDetails[0].trim(), eventDetails[1].trim(), eventDetails[2], false);
            return new AddCommand(eventTask);

        case "find":
            return new FindCommand(commandDetails[1]);

        case "mark":
            String[] indices = fullCommand.substring(5).split(" ");
            assert(indices.length > 0);
            if (indices.length == 1) {
                int markIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
                assert(markIndex >= 0);
                return new MarkCommand(markIndex);
            } else {
                int[] intArray = Arrays.stream(indices)
                        .mapToInt(Integer::parseInt).map(x -> x - 1)
                        .toArray();
                return new MarkCommand(intArray);
            }

        case "unmark":
            int unmarkIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
            assert(unmarkIndex >= 0);
            return new UnmarkCommand(unmarkIndex);

        case "delete":
            int deleteIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
            assert(deleteIndex >= 0);
            return new DeleteCommand(deleteIndex);

        case "list":
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        default:
            return new InvalidCommand();
        }
        return new InvalidCommand();
    }

    /**
     * This method checks if the length of the text list matches the expected length
     *
     * @param textList the split command for checking
     * @param expectedLength the expected length of the textList parameter
     * @throws LuffyException If the expectedLength does not equal to the length of the textList
     */
    public static void checkValidArguments(String[] textList, int expectedLength) throws LuffyException {
        if (textList.length != expectedLength && expectedLength == 2) {
            throw new LuffyException("Your deadline task requires a deadline!");
        } else if (textList.length != expectedLength && expectedLength == 3) {
            throw new LuffyException("Your event task requires a start time and end time!");
        }
    }

    /**
     * This method handles the input from the user when they are
     * using the GUI
     * @param input the input by the user that will be parsed
     */
    public String handleInputFromGui(String input) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        PrintStream pStream = new PrintStream(bStream);
        PrintStream sysStream = System.out;
        System.setOut(pStream);

        LuffyUI luffyUI = new LuffyUI();
        Storage storage = new Storage();
        TaskList taskList = null;
        try {
            taskList = storage.loadFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Command userCommand = LuffyParser.parse(input);
        String mainAction = input.split(" ", 2)[0];

        switch (mainAction) {
        case "bye":
            return "Goodbye! See you soon!";
        default:
            userCommand.executeCmd(luffyUI, storage, taskList);
        }
        System.out.flush();
        System.setOut(sysStream);
        return bStream.toString();
    }
}
