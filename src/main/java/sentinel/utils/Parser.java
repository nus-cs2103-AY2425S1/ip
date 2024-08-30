package sentinel.utils;

import sentinel.api.GeminiAPI;
import sentinel.exception.DeadlineException;
import sentinel.exception.EventException;
import sentinel.exception.SentinelException;
import sentinel.task.Deadline;
import sentinel.task.Event;
import sentinel.task.Task;
import sentinel.task.ToDo;
import sentinel.ui.Ui;
import sentinel.Sentinel;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    public static Sentinel.CommandType parseForCommand(String input) {
        String[] arr = input.split(" ");
        return Sentinel.CommandType.valueOf(arr[0].toLowerCase());
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
                LocalDateTime deadlineDateTime = GeminiAPI.formatDateTime(deadlineTime);
                if (deadlineDateTime != null) {
                    return new Deadline(taskName, deadlineDateTime);
                } else {
                    ui.showDeadlineCommandGuidelines();
                }
            }
            case event -> {
                String fromTime = parseTime(input, "/from");
                String toTime = parseTime(input, "/to");
                LocalDateTime fromDateTime = GeminiAPI.formatDateTime(fromTime);
                LocalDateTime toDateTime = GeminiAPI.formatDateTime(toTime);
                if (fromDateTime == null || toDateTime == null) {
                    ui.showEventCommandGuidelines();
                } else if (fromDateTime.isAfter(toDateTime)){
                    ui.showEventDateOrder();
                } else return new Event(taskName, fromDateTime, toDateTime);
            }
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
                    throw new DeadlineException("Deadline task command requires a '/by' date.");
                }
            }
            case event -> {
                // For 'event', taskName is everything after the command and before "/from"
                int fromIndex = Arrays.asList(stringArr).indexOf("/from");
                if (fromIndex != -1) {
                    taskName = String.join(" ", Arrays.copyOfRange(stringArr, 1, fromIndex)).trim();
                } else {
                    ui.showEventCommandGuidelines();
                    throw new EventException("Event task command requires a '/from' time.");
                }
            }
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
}
