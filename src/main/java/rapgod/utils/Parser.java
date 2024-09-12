package rapgod.utils;

import rapgod.tasks.Deadline;
import rapgod.tasks.Event;
import rapgod.tasks.Task;
import rapgod.tasks.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses the input string into a {@link LocalDateTime} object. The method handles various date and time formats:
     * - "dd/MM/yyyy HHmm" for both date and time.
     * - "MMM dd yyyy" for date only (with default time 00:00).
     * - "MMM dd yyyy h:mma" and "MMM dd yyyy hh:mma" for date and time with optional AM/PM notation.
     *
     * If the input string does not match any of these formats, an {@link IllegalArgumentException} is thrown.
     *
     * @param input The string representing the date and/or time to be parsed.
     * @return A {@link LocalDateTime} object corresponding to the parsed input.
     * @throws IllegalArgumentException If the input string does not match any supported format.
     */
    public static LocalDateTime parseToDateTime(String input) {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");

        try {
            return LocalDateTime.parse(input, dateTimeFormatter1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(input + " 0000", dateTimeFormatter1);
            } catch (DateTimeParseException e2) {
                try {
                    LocalDate date = LocalDate.parse(input, dateTimeFormatter2);
                    return date.atStartOfDay();
                } catch (DateTimeParseException e3) {
                    try {
                        return LocalDateTime.parse(input, dateTimeFormatter3);
                    } catch (DateTimeParseException e4) {
                        try {
                            return LocalDateTime.parse(input, dateTimeFormatter4);
                        } catch (DateTimeParseException e5) {
                            throw new IllegalArgumentException("Invalid date or time format. Please use formats like 'dd/MM/yyyy HHmm' or 'MMM dd yyyy'.");
                        }
                    }
                }
            }
        }
    }

    /**
     * Parses the string representation of a task and returns the corresponding {@link Task} object.
     * The string must be in a specific format with the task type indicated (e.g., "D" for Deadline, "E" for Event).
     *
     * - Deadline tasks are expected to be in the format: "[D][status] description (by: dueDate)"
     * - Event tasks are expected to be in the format: "[E][status] description (from: startDate to: endDate)"
     * - ToDo tasks are expected to be in the format: "[T][status] description"
     *
     * The status is indicated by the character at position 8 (e.g., 'X' for completed).
     *
     * @param taskString The string representation of the task to be parsed.
     * @return The corresponding {@link Task} object (either a {@link Deadline}, {@link Event}, or {@link ToDo}).
     * @throws IllegalArgumentException If the string is null, empty, or does not match the expected format.
     */
    public static Task parseToTask(String taskString) {
        if (taskString == null || taskString.isEmpty()) {
            throw new IllegalArgumentException("String representation cannot be null or empty");
        }

        boolean isDone = taskString.charAt(8) == 'X'; // The status is represented by the second character
        String info = taskString; // The description starts after the brackets

        int descStartIndex = info.indexOf("[ ]") + 4;

        if (taskString.charAt(4) == 'D') {
            String deadlineDesc = info.substring(descStartIndex, info.toLowerCase().indexOf(" (by:"));
            String due = info.substring(info.toLowerCase().indexOf(" (by:") + 6, info.length() - 1);
            Deadline deadline = new Deadline(deadlineDesc, due);
            deadline.setIsDone(isDone);
            return deadline;

        } else if (taskString.charAt(4) == 'E') {
            String eventDesc = info.substring(descStartIndex, info.toLowerCase().indexOf(" (from:"));
            String from = info.substring(info.toLowerCase().indexOf(" (from:") + 8, info.toLowerCase().indexOf(" to:"));
            String to = info.substring(info.toLowerCase().indexOf("to:") + 4, info.length() - 1);
            Event event = new Event(eventDesc, from, to);
            event.setIsDone(isDone);
            return event;

        } else {
            ToDo todo = new ToDo(info.substring(descStartIndex));
            todo.setIsDone(isDone);
            return todo;
        }
    }


}
