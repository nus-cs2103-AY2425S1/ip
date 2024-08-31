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

public final class Parser {
    private final Scanner INPUT_READER = new Scanner(System.in);
    private String input = "";
    public Parser() {}

    private String getInput() {
        input = INPUT_READER.nextLine().toLowerCase();
        return input;
    }

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

    public String getDescription() {
        return getInput();
    }

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

    public LocalDate readDate() {
        getInput();
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public LocalTime readTime() {
        getInput();
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public int getTaskNumber() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
