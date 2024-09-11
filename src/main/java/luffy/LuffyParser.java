package luffy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
            int markIndex = Integer.parseInt(fullCommand.substring(5)) - 1;
            return new MarkCommand(markIndex);

        case "unmark":
            int unmarkIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
            return new UnmarkCommand(unmarkIndex);

        case "delete":
            int deleteIndex = Integer.parseInt(fullCommand.substring(7)) - 1;
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
}
