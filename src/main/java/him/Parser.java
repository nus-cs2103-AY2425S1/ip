package him;

import command.Command;
import command.AddCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.DeleteCommand;
import command.UnknownCommand;

import exceptions.HimException;
import exceptions.InvalidFindFormatException;
import exceptions.InvalidTodoFormatException;
import exceptions.InvalidDeadlineFormatException;
import exceptions.InvalidEventFormatException;
import exceptions.InvalidDateTimeFormatException;
import exceptions.InvalidDeleteFormatException;

import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.format.DateTimeParseException;

/**
 * Class to handle parsing user inputs to Him.
 *
 * @author IsaacPangTH
 */
public class Parser {

    /**
     * Parses a user input and returns a command to be executed.
     *
     * @param input User input string.
     * @return Command to be executed by Him.
     * @throws HimException If the command is formatted wrongly or used in an invalid case.
     */
    public static Command parseUserInput(String input) throws HimException {
        String[] args = input.split(" ", 2);
        String command = args[0];
        //@formatter:off
        return switch (command) {
        case "list" -> new ListCommand();
        case "mark" -> {
            int index = parseMark(args);
            yield new MarkCommand(index);
        }
        case "todo" -> {
            ToDo newTodo = parseTodo(args);
            yield new AddCommand(newTodo);
        }
        case "deadline" -> {
            Deadline newDeadline = parseDeadline(args);
            yield new AddCommand(newDeadline);
        }
        case "event" -> {
            Event newEvent = parseEvent(args);
            yield new AddCommand(newEvent);
        }
        case "delete" -> {
            int index = parseDelete(args);
            yield new DeleteCommand(index);
        }
        case "find" -> {
            String keyword = parseFind(args);
            yield new FindCommand(keyword);
        }
        case "bye" -> new ExitCommand();
        default -> new UnknownCommand(command);
        };
        //@formatter:on
    }

    private static int parseMark(String[] args) {
        return Integer.parseInt(args[1]) - 1;
    }

    private static ToDo parseTodo(String[] args) throws InvalidTodoFormatException {
        try {
            return new ToDo(args[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTodoFormatException();
        }
    }

    private static Deadline parseDeadline(String[] args) throws HimException {
        try {
            String[] details = args[1].split("/by");
            String description = details[0].trim();
            if (description.isEmpty()) {
                throw new InvalidDeadlineFormatException();
            }
            return Deadline.of(description, details[1].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeadlineFormatException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    private static Event parseEvent(String[] args) throws HimException {
        try {
            String[] details = args[1].split("/start");
            String description = details[0].trim();
            String[] interval = details[1].split("/end");

            if (description.isEmpty()) {
                throw new InvalidEventFormatException();
            }

            return new Event(description, interval[0].trim(), interval[1].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidEventFormatException();
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    private static int parseDelete(String[] args) throws InvalidDeleteFormatException {
        try {
            return Integer.parseInt(args[1]) - 1;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDeleteFormatException();
        }
    }

    private static String parseFind(String[] args) throws InvalidFindFormatException {
        try {
            String[] keywords = args[1].split(" ");
            if (keywords.length != 1) {
                throw new InvalidFindFormatException();
            }
            return keywords[0].trim();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFindFormatException();
        }
    }
}
