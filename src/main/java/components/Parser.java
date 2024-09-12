package components;

import command.*;
import exceptions.LightException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static command.Command.CommandType.*;

/**
 * Represents a parser that parses the user input and returns the corresponding command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand The user input to be parsed.
     * @return The corresponding command.
     * @throws LightException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws LightException {
        Task task = null;
        String[] splitedBySlash;
        String[] splitedBySpace = fullCommand.split("\\s+", 2);
        String commandHeader = splitedBySpace[0];
        String commandDescription = null;
        if (splitedBySpace.length == 2) {
            commandDescription = splitedBySpace[1];
        }
        switch (getCommandType(commandHeader)) {
        case BYE:
            return new ExitCommand();
        case MARK:
            try {
                int itemNumber = Integer.parseInt(commandDescription) - 1;
                return new MarkCommand(itemNumber, true);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            break;
        case UNMARK:
            try {
                int itemNumber = Integer.parseInt(commandDescription) - 1;
                return new MarkCommand(itemNumber, false);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            break;
        case LIST:
            return new ListCommand();
        case DELETE:
            try {
                int taskNumber = Integer.parseInt(commandDescription) - 1;
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            break;
        case FIND:
            return new FindCommand(commandDescription);
        case UNDO:
            return new UndoCommand();
        case REDO:
            return new RedoCommand();
        default:
            switch (getCommandType(commandHeader)) {
            case TODO:
                try {
                    task = new Todo(commandDescription);
                } catch (LightException e) {
                    System.out.println(e);
                }

                break;
            case DEADLINE:
                try {
                    splitedBySlash = commandDescription.split("/by ");
                    task = new Deadline(splitedBySlash[0], splitedBySlash[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Not enough arguments");
                } catch (LightException e) {
                    System.out.println(e);
                }

                break;
            case EVENT:
                try {
                    splitedBySlash = commandDescription.split("/from ");
                    String[] splitedBySlashTo = commandDescription.split("/to ");
                    task = new Event(splitedBySlash[0], splitedBySlash[1].substring(0, splitedBySlash[1].indexOf("/to ")).stripTrailing(), splitedBySlashTo[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Not enough arguments");
                } catch (LightException e) {
                    System.out.println(e);
                }

                break;
            }
            if (task != null) {
                return new AddCommand(task);
            }

        }
        throw new LightException("Please key in a valid input");
    }

    /**
     * Parses the input string to a LocalDateTime object.
     *
     * @param input  The input string to be parsed.
     * @param format The format of the input string.
     * @return The LocalDateTime object parsed from the input string.
     */
    public static LocalDateTime dateTimeParser(String input, DateTimeFormatter format) {
        //Define the input format
        try {
            // Parse the input string to a LocalDateTime object
            // Return the LocalDateTime object
            return LocalDateTime.parse(input, format);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use the format: " + format.toString());
            return null;
        }
    }

    public static Command.CommandType getCommandType(String Command) throws LightException {
        switch (Command) {
        case "bye":
            return BYE;
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "list":
            return LIST;
        case "delete":
            return DELETE;
        case "find":
            return FIND;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "undo":
            return UNDO;
        case "redo":
            return REDO;
        default:
            throw new LightException("Invalid command type");
        }
    }

    public static Command.CommandType getCommandTypeFromStorage(char Command) throws LightException {
        switch (Command) {
        case 'T':
            return TODO;
        case 'D':
            return DEADLINE;
        case 'E':
            return EVENT;
        default:
            throw new LightException("Invalid command type");
        }
    }
}
