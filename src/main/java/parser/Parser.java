package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import enumeration.CommandType;
import exception.InvalidIndexException;
import exception.InvalidInputFormatException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;


/**
 * Parser class that handles user inputs
 */
public class Parser {

    public Parser() {

    }



    /**
     * Reads lines of text from a file and returns them as an {@code ArrayList} of strings.
     * Each line from the file is added to the list. If the file cannot be found, an error message
     * is printed, and an empty {@code ArrayList} is returned.
     *
     * @param filepath the path to the file to be read
     * @return an {@code ArrayList} containing the lines of text from the file
     */
    public static ArrayList<String> scan(String filepath) {
        try {
            Scanner sc = new Scanner(new File(filepath));
            ArrayList<String> out = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                out.add(line);
            }
            return out;

        } catch (FileNotFoundException fnfe) {
            System.out.println("____________________________________________________________");
            System.out.println("  Error reading from input file!");

        }
        return new ArrayList<>();
    }

    /**
     * Reads a line of input from the standard input (keyboard) and returns it as a string.
     *
     * @return the input string entered by the user
     */
    public static String scan() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;

    }

    private static String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Extracts the description part from the input string by removing the command prefix.
     * The command prefix is identified and removed from the input, leaving only the description.
     *
     * @param input the input string containing the command and description
     * @return the description part of the input string, with leading and trailing whitespace removed
     */
    public static String getDesc(String input) {
        return input.replaceFirst(getCommand(input), "").trim();
    }

    /**
     * Determines the {@code CommandType} from the provided input string.
     * The input string is parsed to extract the command, which is then compared to
     * known command types to determine the appropriate {@code CommandType}. If the command
     * is not recognized, an {@code InvalidInputFormatException} is thrown.
     *
     * @param input the input string containing the command
     * @return the {@code CommandType} corresponding to the input command
     * @throws InvalidInputFormatException if the input command does not match any known command types
     */
    public static CommandType createCommandFromInput(String input) throws InvalidInputFormatException {
        String command = getCommand(input);
        if (Objects.equals(command, CommandType.BYE.toString())) {
            return CommandType.BYE;
        } else if (Objects.equals(command, CommandType.LIST.toString())) {
            return CommandType.LIST;
        } else if (Objects.equals(command, CommandType.MARK.toString())) {
            return CommandType.MARK;
        } else if (Objects.equals(command, CommandType.UNMARK.toString())) {
            return CommandType.UNMARK;
        } else if (Objects.equals(command, CommandType.DEADLINE.toString())) {
            return CommandType.DEADLINE;
        } else if (Objects.equals(command, CommandType.TODO.toString())) {
            return CommandType.TODO;
        } else if (Objects.equals(command, CommandType.EVENT.toString())) {
            return CommandType.EVENT;
        } else if (Objects.equals(command, CommandType.DELETE.toString())) {
            return CommandType.DELETE;
        } else if (Objects.equals(command, CommandType.FIND.toString())) {
            return CommandType.FIND;
        } else {
            throw new InvalidInputFormatException("Please enter a valid command!");
        }
    }


    /**
     * Creates a {@code Task} object based on the provided input string.
     * The input string is parsed to determine the type of task (TODO, EVENT, or DEADLINE)
     * and its associated details. If the input is invalid or improperly formatted,
     * an {@code InvalidInputFormatException} is thrown.
     *
     * @param input the input string containing task details and command type
     * @return a {@code Task} object created from the input string
     * @throws InvalidInputFormatException if the input format is invalid or does not
     *         match any recognized command types or formats
     */
    public static Task createTaskFromInput(String input) throws InvalidInputFormatException {
        CommandType command = createCommandFromInput(input);
        String desc = getDesc(input);
        switch (command) {
        case TODO:
            if (desc.trim().length() < 2) {
                throw new InvalidInputFormatException("Please enter a valid command!");
            }
            return new Todo(desc);

        case EVENT:
            String[] commandAndPeriod = desc.split("/from");
            if (commandAndPeriod.length < 2) {
                throw new InvalidInputFormatException("Please enter a valid command!");
            }
            String[] period = commandAndPeriod[1].split("/to");
            if (period.length < 2) {
                throw new InvalidInputFormatException("Please enter a valid command!");
            }
            return new Event(commandAndPeriod[0].trim(), period[0].trim(), period[1].trim());

        case DEADLINE:
            String[] commandAndBy = desc.split("/by");
            if (commandAndBy.length < 2) {
                throw new InvalidInputFormatException("Please enter a valid command!");
            }
            return new Deadline(commandAndBy[0].trim(), commandAndBy[1].trim());

        default:
            throw new InvalidInputFormatException("Error creating Task!");
        }

    }

    /**
     * Processes the user's input and performs the corresponding action on the TaskList.
     *
     * @param input The command input from the user, which can include actions such as
     *              LIST, MARK, UNMARK, DELETE, FIND, TODO, EVENT, DEADLINE, or BYE.
     * @param tasks The TaskList that holds the tasks to be modified or queried.
     *
     * @return A String representing the outcome of the command, which could be:
     *         - A farewell message (for BYE)
     *         - A list of tasks (for LIST)
     *         - Confirmation of task modification (for MARK, UNMARK, DELETE)
     *         - The result of a task search (for FIND)
     *         - Confirmation of task creation (for TODO, EVENT, DEADLINE)
     *
     * @throws InvalidInputFormatException If the input command is not valid or recognized.
     * @throws InvalidIndexException If an operation requires a task index, but the index is out of bounds.
     */
    public static String performActionOnTaskList(String input, TaskList tasks) throws InvalidInputFormatException, InvalidIndexException {
        CommandType command = createCommandFromInput(input);
        String desc = Parser.getDesc(input);

        switch (command) {
        case BYE:
            return "  Bye. Hope to see you again soon!";
        case LIST:
            return tasks.list();
        case MARK:
            return tasks.markTask(Integer.parseInt(desc) - 1);
        case UNMARK:
            return tasks.unMarkTask(Integer.parseInt(desc) - 1);
        case DELETE:
            return tasks.deleteTask(Integer.parseInt(desc) - 1);
        case FIND:
            return tasks.findTasks(desc);
        case TODO:
        case EVENT:
        case DEADLINE:
            Task task = createTaskFromInput(input);
            return tasks.addTask(task);
        default:
            throw new InvalidInputFormatException("Please enter a valid command!");
        }
    }

}
