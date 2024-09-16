package elara.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import elara.command.AddCommand;
import elara.command.Command;
import elara.command.DeleteCommand;
import elara.command.ExitCommand;
import elara.command.FindCommand;
import elara.command.ListCommand;
import elara.command.MarkCommand;
import elara.command.UnmarkCommand;
import elara.task.DeadlineException;
import elara.task.DeadlineTask;
import elara.task.EventException;
import elara.task.EventTask;
import elara.task.InvalidInputException;
import elara.task.ToDoException;
import elara.task.ToDoTask;

/**
 * The {@code Parser} class is responsible for interpreting user input and returning
 * the corresponding command. This class helps in determining what the user wants to do.
 */
public class Parser {
    public static String getCommandType(String input) {
        assert input != null;
        String[] parts = input.split(" ", 2);
        return parts[0].toLowerCase();
    }

    /**
     * Parses the user's input and returns the appropriate command object.
     *
     * @param input The full input string from the user.
     * @return A {@code Command} object representing the user's desired action.
     * @throws InvalidInputException If the input does not match any known commands.
     */
    public static Command parse(String input) throws InvalidInputException {
        String command = getCommandType(input);
        return switch (command) {
        case "bye" -> new ExitCommand();
        case "find" -> new FindCommand(input);
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(input);
        case "unmark" -> new UnmarkCommand(input);
        case "delete" -> new DeleteCommand(input);
        case "todo", "deadline", "event" -> new AddCommand(command, input);
        default -> throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n"
                    + "Try one of our commands: list mark unmark bye deadline todo event");
        };
    }

    /**
     * Parses the given input string into a LocalDateTime object
     *
     * @param input the date and time string to parse.
     * @return the corresponding LocalDateTime object
     * @throws InvalidInputException if the input format is invalid or cannot be parsed
     */
    public static LocalDateTime parseDateTime(String input) throws InvalidInputException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date/time format. Use: yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Parses the given input string into a ToDoTask object
     *
     * @param details the string after the command word.
     * @return the corresponding ToDoTask object
     * @throws InvalidInputException if description is empty
     */
    public static ToDoTask parseToDo(String details) throws InvalidInputException {
        if (details == null || details.isEmpty()) {
            throw new ToDoException();
        }
        return new ToDoTask(details);
    }

    /**
     * Parses the given input string into a DeadlineTask object
     *
     * @param details the string after the command word.
     * @return the corresponding DeadlineTask object.
     * @throws InvalidInputException if string not in required format.
     */
    public static DeadlineTask parseDeadline(String details) throws InvalidInputException {
        if (details == null || details.isEmpty()) {
            throw new DeadlineException();
        }
        String[] deadlineArgs = details.split(" /by ");
        if (deadlineArgs.length != 2) {
            throw new DeadlineException();
        }
        String description = deadlineArgs[0];
        String deadlineString = deadlineArgs[1];
        LocalDateTime deadline = parseDateTime(deadlineString);
        return new DeadlineTask(description, deadline, false);
    }

    /**
     * Parses the given input into an EventTask object
     *
     * @param details the string after the command word.
     * @return the corresponding EventTask object.
     * @throws InvalidInputException if string not in required format.
     */
    public static EventTask parseEvent(String details) throws InvalidInputException {
        if (details == null || details.isEmpty()) {
            throw new EventException();
        }

        String[] eventArgs = details.split(" /from ");
        if (eventArgs.length != 2) {
            throw new EventException();
        }

        String[] timeArgs = eventArgs[1].split(" /to ");
        if (timeArgs.length != 2) {
            throw new EventException();
        }
        LocalDateTime start = Parser.parseDateTime(timeArgs[0].trim());
        LocalDateTime end = Parser.parseDateTime(timeArgs[1].trim());
        return new EventTask(eventArgs[0], start, end, false);
    }
}

