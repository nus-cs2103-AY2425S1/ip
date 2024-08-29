package echo.backend;

import echo.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> DATEFORMATS =
            new ArrayList<>(
                    Arrays.asList(
                            "yyyy-M-d",
                            "d/M/yyyy",
                            "yyyy/M/d",
                            "d-M-yyyy",
                            "d MMM yyyy",
                            "MMM d yyyy"));
    private Ui ui;
    public Parser(Ui ui) {
        this.ui = ui;
    }
    public void parseInput(String userInput) {
        // Parses user input
        String[] userInputs = userInput.split(" ", 2);
        Command command = Command.fromString(userInputs[0]);
        String arg = userInputs.length > 1 ? userInputs[1] : "";

        // Handles command
        switch (command) {
        case UNKNOWN:
            ui.handleUnknown();
            break;
        case LIST:
            ui.handleList();
            break;
        case MARK:
            ui.handleMark(arg);
            break;
        case UNMARK:
            ui.handleUnmark(arg);
            break;
        case TODO:
            ui.handleTodo(arg);
            break;
        case DEADLINE:
            String[] parsedDeadline = parseDeadline(arg);
            ui.handleDeadline(
                    parsedDeadline[0].trim(),
                    parsedDeadline.length > 1 ? parsedDeadline[1].trim() : ""
            );
            break;
        case EVENT:
            String[] parsedEvent = parseEventFrom(arg);
            ui.handleEvent(
                    parsedEvent[0].trim(),
                    parsedEvent.length > 1 ? parsedEvent[1] : ""
            );
            break;
        case DELETE:
            ui.handleDelete(arg);
            break;
        case BYE:
            ui.handleBye();
            break;
        }
    }
    private String[] parseEventFrom(String arg) {
        return arg.split("/from ");
    }
    public String[] parseEventTo(String arg) {
        return arg.split("/to ");
    }
    private String[] parseDeadline(String arg) {
        return arg.split("/by ");
    }
    public LocalDate parseDateTime(String s) throws DateTimeParseException {
        DateTimeFormatter formatter;
        for (String pattern : DATEFORMATS) {
            formatter = DateTimeFormatter.ofPattern(pattern);
            try {
                LocalDate localDate = LocalDate.parse(s, formatter);
                return localDate;
            } catch (DateTimeParseException e) {
            }
        }
        throw new DateTimeParseException("Cannot parse string", s, 0);
    }
    private enum Command {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        LIST,
        BYE,
        UNKNOWN;
        public static Command fromString(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }
}
