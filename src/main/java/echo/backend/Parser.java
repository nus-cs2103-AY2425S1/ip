package echo.backend;

import echo.StateType;
import echo.Ui;
import echo.task.TaskType;

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
    private static final List<String> DATE_FORMATS =
            new ArrayList<>(
                    Arrays.asList(
                            "yyyy-M-d",
                            "d/M/yyyy",
                            "yyyy/M/d",
                            "d-M-yyyy",
                            "d MMM yyyy",
                            "MMM d yyyy"));
    private Ui ui;
    private StateType statetype;
    private String[] tempStrings; // Description, start, end, deadline
    /**
     * Constructs a Parser object with the specified Ui.
     *
     * @param ui the Ui object that handles user interaction and command execution
     */
    public Parser(Ui ui) {
        this.ui = ui;
        this.tempStrings = new String[] {"", "", "", ""};
    }
    /**
     * Parses the user input and delegates the command to the Ui for execution.
     *
     * @param userInput the input string provided by the user
     */
    public String parseInput(String userInput) {
        // Parses user input
        String[] userInputs = userInput.split(" ", 2);
        Command command = Command.fromString(userInputs[0]);
        String arg = userInputs.length > 1 ? userInputs[1] : "";

        // Handles command
        switch (command) {
        case UNKNOWN:
            switch (statetype) {
            case TODO_DESCRIPTION:
                changeState(StateType.NO_STATE);
                return ui.handleTodo(userInput);
            case DEADLINE_DESCRIPTION:
                changeState(StateType.NO_STATE);
                return ui.handleDeadline(userInput, tempStrings[3]);
            case DEADLINE_DEADLINE:
                changeState(StateType.NO_STATE);
                return ui.handleDeadline(tempStrings[0], userInput.trim());
            case EVENT_DESCRIPTION:
                changeState(StateType.NO_STATE);
                return ui.handleEvent(userInput, tempStrings[1]);
            case EVENT_START:
                changeState(StateType.NO_STATE);
                return ui.handleEvent(tempStrings[0], userInput);
            case EVENT_END:
                changeState(StateType.NO_STATE);
                return ui.handleEvent(tempStrings[0], tempStrings[1]+ " /to " + userInput);
            }
            return ui.handleUnknown();
        case LIST:
            return ui.handleList();
        case MARK:
            return ui.handleMark(arg);
        case UNMARK:
            return ui.handleUnmark(arg);
        case TODO:
            return ui.handleTodo(arg);
        case DEADLINE:
            String[] parsedDeadline = parseDeadline(arg);
            return ui.handleDeadline(
                    parsedDeadline[0].trim(),
                    parsedDeadline.length > 1 ? parsedDeadline[1].trim() : ""
            );
        case EVENT:
            String[] parsedEvent = parseEventFrom(arg);
            return ui.handleEvent(
                    parsedEvent[0].trim(),
                    parsedEvent.length > 1 ? parsedEvent[1] : ""
            );
        case FIND:
            return ui.handleFind(arg);
        case DELETE:
            return ui.handleDelete(arg);
        case BYE:
            return ui.handleBye();
        }
        return "";
    }
    public void changeState(StateType s) {
        this.statetype = s;
    }
    public void keepTemp(String s, int index) {
        tempStrings[index] = s;
    }
    public void resetTemp() {
        this.tempStrings = new String[]{"", "", "", ""};
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
        for (String pattern : DATE_FORMATS) {
            formatter = DateTimeFormatter.ofPattern(pattern);
            try {
                LocalDate localDate = LocalDate.parse(s, formatter);
                return localDate;
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
        FIND,
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
