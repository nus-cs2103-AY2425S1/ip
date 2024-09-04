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
import colress.command.UncheckCommand;
import colress.exception.UnknownCommandException;

/**
 * Represents the Parser of the Ui of the Colress chatbot.
 */
public final class Parser {
    public Parser() {}

    /**
     * Reads user input and returns the corresponding Command.
     * Throws an UnknownCommandException if an invalid command is detected.
     */
    public Command getCommand(String input) throws UnknownCommandException {
        switch (input.toLowerCase()) {
        case "add":
            return new AddCommand();
        case "check":
            return new CheckCommand();
        case "date":
            return new DateCommand();
        case "delete":
            return new DeleteCommand();
        case "bye":
            return new ExitCommand();
        case "find":
            return new FindCommand();
        case "list":
            return new ListCommand();
        case "uncheck":
            return new UncheckCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Reads user input and returns the corresponding task type.
     * Throws an UnknownTaskTypeException if an invalid task type is detected.
     */
    public TaskType getTaskType(String input) throws IllegalArgumentException {
        return TaskType.valueOf(input.toUpperCase());
    }

    /**
     * Reads user input and returns the corresponding LocalDate object.
     * Throws a DateTimeParseException if an invalid task type is detected.
     */
    public LocalDate readDate(String input) throws DateTimeParseException {
        return LocalDate.parse(input);
    }

    /**
     * Reads user input and returns the corresponding LocalTime object.
     * Throws a DateTimeParseException if an invalid task type is detected.
     */
    public LocalTime readTime(String input) throws DateTimeParseException {
        return LocalTime.parse(input);
    }

    /**
     * Reads user input and returns the corresponding task number.
     * Throws a NumberFormatException if a number is not detected.
     */
    public int getTaskNumber(String input) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        return Integer.parseInt(input);
    }
}
