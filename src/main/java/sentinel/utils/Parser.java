package sentinel.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import sentinel.Sentinel;
import sentinel.api.GeminiApi;
import sentinel.exception.DeadlineException;
import sentinel.exception.EventException;
import sentinel.exception.InvalidCommandException;
import sentinel.exception.SentinelException;
import sentinel.task.Deadline;
import sentinel.task.Event;
import sentinel.task.Task;
import sentinel.task.ToDo;
import sentinel.ui.Ui;

/**
 * The Parser class is responsible for parsing user input and converting it into commands or tasks.
 * It includes methods for parsing command types, task names, deadlines, events, and indices.
 */
public class Parser {
    /**
     * Parses the user input to determine the command type.
     *
     * @param input The user's input string.
     * @return The command type determined from the input.
     */
    public static Sentinel.CommandType parseForCommand(String ... input) throws SentinelException {
        String[] arr = input[0].split(" ");
        try {
            return Sentinel.CommandType.valueOf(arr[0].toLowerCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the user input to create a Task object based on the command type.
     *
     * @param commandType The type of command (e.g., todo, deadline, event).
     * @param input The user's input string.
     * @param ui The user interface object for displaying messages.
     * @return A Task object if the input is valid; null otherwise.
     * @throws SentinelException If the input is invalid or the task cannot be created.
     */
    public static Task parseTask(Sentinel.CommandType commandType, String input, Ui ui) throws SentinelException {
        String taskName = parseTaskName(commandType, input, ui);
        if (taskName.isEmpty()) {
            ui.showEmptyTaskNameError(commandType);
            return null;
        }
        switch (commandType) {
        case todo -> {
            return new ToDo(taskName);
        }
        case deadline -> {
            String deadlineTime = parseTime(input, "/by");
            LocalDateTime deadlineDateTime;
            try {
                deadlineDateTime = LocalDateTime.parse(deadlineTime);
            } catch (DateTimeParseException e) {
                deadlineDateTime = GeminiApi.formatDateTime(deadlineTime);
            }
            if (deadlineDateTime != null) {
                return new Deadline(taskName, deadlineDateTime);
            } else {
                ui.showDeadlineCommandGuidelines();
                throw new DeadlineException(SentinelString.stringDeadlineCommandGuidelines());
            }
        }
        case event -> {
            String fromTime = parseTime(input, "/from", "/to");
            String toTime = parseTime(input, "/to");
            LocalDateTime fromDateTime = null;
            LocalDateTime toDateTime;
            try {
                fromDateTime = LocalDateTime.parse(fromTime);
                toDateTime = LocalDateTime.parse(toTime);
            } catch (DateTimeParseException e) {
                if (fromDateTime == null) {
                    fromDateTime = GeminiApi.formatDateTime(fromTime);
                }
                toDateTime = GeminiApi.formatDateTime(toTime);
            }
            if (fromDateTime == null || toDateTime == null) {
                ui.showEventCommandGuidelines();
                throw new EventException(SentinelString.stringEventCommandGuidelines());
            } else if (fromDateTime.isAfter(toDateTime)) {
                ui.showEventDateOrder();
                throw new EventException(SentinelString.stringEventDateOrder());
            } else {
                return new Event(taskName, fromDateTime, toDateTime);
            }
        }
        default -> { }
        }
        return null;
    }

    /**
     * Parses the task name from the user input based on the command type.
     *
     * @param commandType The type of command (e.g., todo, deadline, event).
     * @param input The user's input string.
     * @param ui The user interface object for displaying messages.
     * @return The parsed task name as a string.
     * @throws SentinelException If the input is invalid or the task name cannot be parsed.
     */
    public static String parseTaskName(Sentinel.CommandType commandType, String input, Ui ui) throws SentinelException {
        String[] stringArr = input.split(" ");
        String taskName = "";

        switch (commandType) {
        case todo -> taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, stringArr.length)).trim();

        case deadline -> {
            // For 'deadline', taskName is everything after the command and before "/by"
            int byIndex = Arrays.asList(stringArr).indexOf("/by");
            if (byIndex != -1) {
                taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, byIndex)).trim();
            } else {
                ui.showDeadlineCommandGuidelines();
                throw new DeadlineException(SentinelString.stringDeadlineCommandGuidelines());
            }
        }
        case event -> {
            // For 'event', taskName is everything after the command and before "/from"
            int fromIndex = Arrays.asList(stringArr).indexOf("/from");
            if (fromIndex != -1) {
                taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, fromIndex)).trim();
            } else {
                ui.showEventCommandGuidelines();
                throw new EventException(SentinelString.stringEventCommandGuidelines());
            }
        }
        default -> { }
        }

        return taskName;
    }

    /**
     * Parses the time from the user input based on a given delimiter (e.g., "/by", "/from").
     *
     * @param input The user's input string.
     * @param delimiter The delimiter used to separate the time in the input.
     * @return The parsed time as a string.
     */
    public static String parseTime(String input, String delimiter) {
        String[] parts = input.split(delimiter);
        return parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Parses the time from the user input based on a given delimiter (e.g., "/by", "/from").
     * For the "/from" case, it will capture the time between "/from" and "/to".
     *
     * @param input The user's input string.
     * @param delimiter The delimiter used to start parsing the time in the input (e.g., "/from").
     * @param endDelimiter The delimiter used to end parsing the time in the input (e.g., "/to").
     * @return The parsed time as a string.
     */
    public static String parseTime(String input, String delimiter, String endDelimiter) {
        String[] parts = input.split(delimiter);
        if (parts.length > 1) {
            String remainingInput = parts[1].trim(); // get the part after the start delimiter
            // If there's an end delimiter, split by it and take the first part (i.e., before "/to")
            String[] subParts = remainingInput.split(endDelimiter);
            return subParts[0].trim(); // return the text between "/from" and "/to"
        }
        return "";
    }


    /**
     * Parses an index from the user input, typically used for identifying task numbers.
     *
     * @param input The user's input string.
     * @return The parsed index as an integer, or -1 if parsing fails.
     */
    public static int parseIndex(String input) {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Parses the keyword from the user input for the 'find' command.
     *
     * @param input The user's input string.
     * @return The parsed keyword as a string.
     */
    public static String parseKeyword(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1].trim() : "";
    }
}
