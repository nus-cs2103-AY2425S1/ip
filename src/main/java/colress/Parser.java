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
import colress.command.FindCommand;
import colress.command.ListCommand;
import colress.command.UncheckCommand;
import colress.exception.UnknownCommandException;
import colress.exception.UnknownTaskTypeException;

public final class Parser {
    private Scanner scanner = new Scanner(System.in);
    private String input = "";
    public Parser() {}

    private String getInput() {
        input = scanner.nextLine().toLowerCase();
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

    public String getString() {
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
