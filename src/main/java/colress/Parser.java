package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import colress.command.AddCommand;
import colress.command.CheckCommand;
import colress.command.Command;
import colress.command.DateCommand;
import colress.command.DeleteCommand;
import colress.command.ExitCommand;
import colress.command.FindCommand;
import colress.command.ListCommand;
import colress.command.ToggleCommand;
import colress.command.UncheckCommand;
import colress.exception.EmptyInputException;
import colress.exception.UnknownCommandException;

/**
 * Represents the Parser of the Ui of the Colress chatbot.
 */
public final class Parser {
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_CHECK = "check";
    public static final String COMMAND_DATE = "date";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_TOGGLE = "toggle";
    public static final String COMMAND_UNCHECK = "uncheck";

    public Parser() {}

    /**
     * Reads user input and returns the corresponding Command.
     * Throws an UnknownCommandException if an invalid command is detected.
     */
    public Command getCommand(String input) throws UnknownCommandException {
        input = input.trim();
        switch (input.toLowerCase()) {
        case COMMAND_ADD:
            return new AddCommand();
        case COMMAND_CHECK:
            return new CheckCommand();
        case COMMAND_DATE:
            return new DateCommand();
        case COMMAND_DELETE:
            return new DeleteCommand();
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_FIND:
            return new FindCommand();
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_UNCHECK:
            return new UncheckCommand();
        case COMMAND_TOGGLE:
            return new ToggleCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Reads user input and returns the corresponding Command.
     * Throws an UnknownCommandException if an invalid command is detected.
     */
    public Command parseCommand(String input) throws UnknownCommandException {
        input = input.trim();
        int whitespace = input.indexOf(" ");
        String commandWord;
        String[] arguments;

        if (whitespace == -1) {
            commandWord = input;
            arguments = null;
        } else {
            commandWord = input.substring(0, whitespace);
            arguments = input.substring(whitespace + 1).split(",");
        }

        switch (commandWord.toLowerCase()) {
        case COMMAND_ADD:
            return new AddCommand(arguments);
        case COMMAND_CHECK:
            return new CheckCommand(arguments);
        case COMMAND_DATE:
            return new DateCommand(arguments);
        case COMMAND_DELETE:
            return new DeleteCommand(arguments);
        case COMMAND_EXIT:
            return new ExitCommand();
        case COMMAND_FIND:
            return new FindCommand(arguments);
        case COMMAND_LIST:
            return new ListCommand();
        case COMMAND_UNCHECK:
            return new UncheckCommand(arguments);
        case COMMAND_TOGGLE:
            return new ToggleCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Takes an input and returns the corresponding task type.
     * Throws an UnknownTaskTypeException if an invalid task type is detected.
     */
    public TaskType getTaskType(String input) throws IllegalArgumentException {
        input = input.trim();
        return TaskType.valueOf(input.toUpperCase());
    }

    /**
     * Takes an input and returns the corresponding LocalDate object.
     * Throws a DateTimeParseException if an invalid task type is detected.
     */
    public LocalDate readDate(String input) throws DateTimeParseException {
        input = input.trim();
        return LocalDate.parse(input);
    }

    /**
     * Takes an input and returns the corresponding LocalTime object.
     * Throws a DateTimeParseException if an invalid task type is detected.
     */
    public LocalTime readTime(String input) throws DateTimeParseException {
        input = input.trim();
        return LocalTime.parse(input);
    }

    /**
     * Takes an input and returns the corresponding task number.
     * Throws a NumberFormatException if a number is not detected.
     */
    public int[] getTaskNumber(String input) throws NumberFormatException {
        input = input.trim();
        String[] inputs = input.split(" ");
        int[] result = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            result[i] = Integer.parseInt(inputs[i]);
        }
        return result;
    }

    public String getString(String input) throws EmptyInputException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new EmptyInputException();
        }
        return input;
    }
}
