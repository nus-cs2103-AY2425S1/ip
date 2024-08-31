package colress;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

import colress.command.AddCommand;
import colress.command.CheckCommand;
import colress.command.Command;
import colress.command.DateCommand;
import colress.command.DeleteCommand;
import colress.command.ExitCommand;
import colress.command.ListCommand;
import colress.command.UncheckCommand;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

/**
 * Represents the Parser of the Ui of the Colress chatbot.
 */
public final class Parser {
    private final Scanner INPUT_READER = new Scanner(System.in);
    private String input = "";
    public Parser() {}

    private String getInput() {
        input = INPUT_READER.nextLine().toLowerCase();
        return input;
    }

    /**
     * Reads user input and returns the corresponding Command.
     * Throws an UnknownCommandException if an invalid command is detected.
     */
    public Command getCommand() throws UnknownCommandException {
        getInput();
        switch (input) {
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
        case "list":
            return new ListCommand();
        case "uncheck":
            return new UncheckCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Reads user input and returns the corresponding description.
     */
    public String getDescription() {
        return getInput();
    }

    /**
     * Reads user input and returns the corresponding task type.
     * Throws an UnknownTaskTypeException if an invalid task type is detected.
     */
    public String getTaskType() throws UnknownTaskTypeException {
        String result = getInput();
        if (Objects.equals(result, "todo")
                || Objects.equals(result, "deadline")
                || Objects.equals(result, "event")) {
            return result;
        } else {
            throw new UnknownTaskTypeException();
        }
    }

    /**
     * Reads user input and returns the corresponding LocalDate object.
     * Throws a DateTimeParseException if an invalid task type is detected.
     */
    public LocalDate readDate() throws DateTimeParseException {
        return LocalDate.parse(getInput());
    }

    /**
     * Reads user input and returns the corresponding LocalTime object.
     * Throws a DateTimeParseException if an invalid task type is detected.
     */
    public LocalTime readTime() throws DateTimeParseException {
        return LocalTime.parse(getInput());
    }

    /**
     * Reads user input and returns the corresponding task number.
     * Throws a NumberFormatException if a number is not detected.
     */
    public int getTaskNumber() throws NumberFormatException {
        return Integer.parseInt(getInput());
    }
}
