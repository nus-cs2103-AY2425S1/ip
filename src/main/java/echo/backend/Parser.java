package echo.backend;

import echo.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * The Parser class handles the parsing of user input and command processing.
 * It interprets commands, manages date formatting, and interacts with the Ui class
 * to communicate with the user and execute the corresponding actions.
 */
public class Parser {
    private static List<String> dateTimeFormats =
            new ArrayList<>(
                    Arrays.asList(
                            "yyyy-M-d",
                            "d/M/yyyy",
                            "yyyy/M/d",
                            "d-M-yyyy",
                            "d MMM yyyy",
                            "MMM d yyyy"));
    private Ui ui;
    /**
     * Constructs a Parser object with the specified Ui.
     *
     * @param ui the Ui object that handles user interaction and command execution
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }
    /**
     * Parses the user input and delegates the command to the Ui for execution.
     *
     * @param userInput the input string provided by the user
     */
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
    /**
     * Splits an event description from the "/from" keyword to separate the start date.
     *
     * @param arg the event description possibly containing the "/from" keyword
     * @return a String array containing the description and start date
     */
    private String[] parseEventFrom(String arg) {
        return arg.split("/from ");
    }
    /**
     * Splits an event description from the "/to" keyword to separate the end date.
     *
     * @param arg the event description possibly containing the "/to" keyword
     * @return a String array containing the start date and end date
     */
    public String[] parseEventTo(String arg) {
        return arg.split("/to ");
    }
    /**
     * Splits a deadline description from the "/by" keyword to separate the deadline date.
     *
     * @param arg the deadline description possibly containing the "/by" keyword
     * @return a String array containing the description and deadline date
     */
    private String[] parseDeadline(String arg) {
        return arg.split("/by ");
    }
    /**
     * Parses a date string into a LocalDate object based on predefined date formats.
     *
     * @param s the date string to be parsed
     * @return the parsed LocalDate object
     * @throws DateTimeParseException if the date string cannot be parsed with any of the formats
     */
    public LocalDate parseDate(String s) throws DateTimeParseException {
        DateTimeFormatter formatter;
        for (String pattern : dateTimeFormats) {
            formatter = DateTimeFormatter.ofPattern(pattern);
            try {
                LocalDate ld = LocalDate.parse(s, formatter);
                return ld;
            } catch (DateTimeParseException e) {
            }
        }
        throw new DateTimeParseException("Cannot parse string", s, 0);
    }
    /**
     * Enum representing the different commands that can be parsed from user input.
     */
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
        /**
         * Converts a string representing a user command into a corresponding Command enum value.
         *
         * @param command the command string to be converted
         * @return the corresponding Command enum value, or UNKNOWN if the string does not match any command
         */
        public static Command fromString(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }
}
