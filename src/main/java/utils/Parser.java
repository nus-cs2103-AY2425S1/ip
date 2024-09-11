package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatterboxerrors.ChatterBoxDeadlineError;
import chatterboxerrors.ChatterBoxDeleteError;
import chatterboxerrors.ChatterBoxError;
import chatterboxerrors.ChatterBoxEventError;
import chatterboxerrors.ChatterBoxFindError;
import chatterboxerrors.ChatterBoxInvalidDateError;
import chatterboxerrors.ChatterBoxInvalidDateTimeError;
import chatterboxerrors.ChatterBoxMarkError;
import chatterboxerrors.ChatterBoxToDoError;


/**
 * Represents a static Parser that handles all commands and user inputs.
 */
public class Parser {
    /**
     * Process a command for ChatterBox.
     * @param input A string representing the command.
     * @return An array of strings that have the command along with details.
     * @throws ChatterBoxError Handles different errors from ChatterBox.
     */
    public static String[] processInput(String input) throws ChatterBoxError {
        String[] userCommand = new String[4];
        String[] command = input.split(" ", 2);
        Commands commandType;
        try {
            commandType = Commands.valueOf(command[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ChatterBoxError();
        }
        userCommand[0] = command[0];
        assert userCommand[0] != null : "Error processing User Commands";
        switch (commandType) {
        case BYE, LIST:
            if (command.length > 1) {
                throw new ChatterBoxError();
            }
            break;
        case MARK, UNMARK, DELETE:
            try {
                int taskNum = Integer.parseInt(command[1]) - 1;
                userCommand[1] = String.valueOf(taskNum);
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (commandType.equals(Commands.DELETE)) {
                    throw new ChatterBoxDeleteError();
                } else {
                    throw new ChatterBoxMarkError();
                }
            }
        case TODO, FIND:
            try {
                if (command[1] != "") {
                    userCommand[1] = command[1];
                    break;
                } else {
                    if (commandType.equals(Commands.TODO)) {
                        throw new ChatterBoxToDoError();
                    } else {
                        throw new ChatterBoxFindError();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (commandType.equals(Commands.TODO)) {
                    throw new ChatterBoxToDoError();
                } else {
                    throw new ChatterBoxFindError();
                }
            }
        case DEADLINE:
            try {
                String[] details = oneTimeExtractor(command[1]);
                userCommand[1] = details[0];
                userCommand[2] = details[1];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatterBoxDeadlineError();
            } catch (ChatterBoxDeadlineError e) {
                throw e;
            }
        case EVENT:
            try {
                String[] details = twoTimeExtractor(command[1]);
                userCommand[1] = details[0];
                userCommand[2] = details[1];
                userCommand[3] = details[2];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatterBoxEventError();
            } catch (ChatterBoxEventError e) {
                throw e;
            }
        default:
            throw new ChatterBoxError();
        }
        return userCommand;
    }

    /**
     * Processes inputs coming from a save file.
     * @param input String inputs from a save file.
     * @return An array of strings that have the command along with details.
     * @throws ChatterBoxError Handles different errors from ChatterBox.
     */
    public static String[] processSaveInput(String input) throws ChatterBoxError {
        String[] userCommand = new String[2];
        try {
            String[] command = input.split(", ", 2);
            userCommand[0] = command[1];
            if (command[0].equals("done")) {
                userCommand[1] = "mark ";
            } else {
                userCommand[1] = null;
            }
            return userCommand;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatterBoxError("Unable to parse line: " + input + "\n");
        }
    }

    /**
     * Process and extracts name and one time from the input string for Deadline.
     * @param input A string containing the name and time of a Deadline.
     * @return An array containing the name and by time.
     * @throws ChatterBoxDeadlineError Specific Error relating to use of Deadline.
     */
    public static String[] oneTimeExtractor(String input) throws ChatterBoxDeadlineError {
        String oneTimeRegex = "^(.*) /by (.*)$";
        Matcher oneTimeMatcher = Pattern.compile(oneTimeRegex).matcher(input);
        String[] parsedNameTime = new String[2];
        if (oneTimeMatcher.find()) {
            parsedNameTime[0] = oneTimeMatcher.group(1);
            parsedNameTime[1] = oneTimeMatcher.group(2);
            assert parsedNameTime[0] != null : "Name should not be null";
            assert parsedNameTime[1] != null : "Time should not be null";
            return parsedNameTime;
        } else {
            throw new ChatterBoxDeadlineError();
        }
    }

    /**
     * Process and extracts name and one time from the input string for Event.
     * @param input A string containing the name and time of an Event.
     * @return An array containing the name, from time and by time.
     * @throws ChatterBoxEventError Specific Error relating to use of Event.
     */
    public static String[] twoTimeExtractor(String input) throws ChatterBoxEventError {
        String twoTimeRegex = "^(.*) /from (.*) /to (.*)$";
        Matcher twoTimeMatcher = Pattern.compile(twoTimeRegex).matcher(input);
        String[] parsedNameTime = new String[3];
        if (twoTimeMatcher.find()) {
            parsedNameTime[0] = twoTimeMatcher.group(1);
            parsedNameTime[1] = twoTimeMatcher.group(2);
            parsedNameTime[2] = twoTimeMatcher.group(3);
            assert parsedNameTime[0] != null : "Name should not be null";
            assert parsedNameTime[1] != null : "Start time should not be null";
            assert parsedNameTime[2] != null : "End time should not be null";
            return parsedNameTime;
        } else {
            throw new ChatterBoxEventError();
        }
    }

    /**
     * Checks and processes a specific format of time for use in Chatterbox.
     * @param dateString A string of specific format.
     * @return A LocalDateTime object containing date and time.
     * @throws ChatterBoxInvalidDateTimeError An error to specify the format for Date and Time.
     * @throws ChatterBoxInvalidDateError An error to specify the format for Date.
     */
    public static LocalDateTime processDateTime(
            String dateString) throws ChatterBoxInvalidDateTimeError, ChatterBoxInvalidDateError {
        DateTimeFormatter dateTimeFormatter;
        boolean containsTime;
        if (dateString.trim().contains(" ")) { // Contains time
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            containsTime = true;
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            containsTime = false;
        }
        try {
            if (containsTime) {
                return LocalDateTime.parse(dateString.trim(), dateTimeFormatter);
            } else {
                return LocalDate.parse(dateString.trim(), dateTimeFormatter).atStartOfDay();
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            if (containsTime) {
                throw new ChatterBoxInvalidDateTimeError();
            } else {
                throw new ChatterBoxInvalidDateError();
            }
        }
    }
}
