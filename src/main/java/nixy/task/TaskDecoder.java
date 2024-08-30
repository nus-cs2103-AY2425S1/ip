package nixy.task;

import java.time.LocalDate;

/**
 * TaskParser is responsible for parsing tasks from strings in save file format
 * It provides static methods for parsing tasks.
 */
public class TaskDecoder {
    /**
     * Parses a task from the specified string.
     *
     * @param taskString The string representation of the task.
     * @return The task parsed from the string.
     * @throws IllegalArgumentException If the task string is invalid.
     */
    public static Task parseTask(String taskString) {
        String[] taskParts = taskString.split("\\|", 3);
        if (taskParts.length < 3) {
            throw new IllegalArgumentException("Invalid task string: " + taskString);
        }
        String taskType = taskParts[0];
        Boolean taskIsDone = taskParts[1].equals("X");
        String taskMetadata = taskParts[2];

        switch (taskType) {
        case "T":
            // Metadata for todo tasks is just the description
            return new TodoTask(taskMetadata, taskIsDone);
        case "D":
            return parseDeadlineTask(taskMetadata, taskIsDone);
        case "E":
            return parseEventTask(taskMetadata, taskIsDone);
        default:
            throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }

    /**
     * Parses a deadline task from the specified string.
     * Deadline task contains a description and a deadline.
     * @param taskMetadata The string representation of the deadline task without the task type.
     * @param isDone The done status of the task.
     * @return The deadline task parsed from the string.
     * @throws IllegalArgumentException If the deadline task string is invalid.
     */
    private static DeadlineTask parseDeadlineTask(String taskMetadata, boolean isDone) {
        String[] parts = taskMetadata.split("\\|", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid deadline task string: " + taskMetadata);
        }
        String description = parts[0];
        LocalDate deadline = LocalDate.parse(parts[1]);
        return new DeadlineTask(description, deadline, isDone);
    }

    /**
     * Parses an event task from the specified string.
     * Event task contains a description, an event start time, and an event end time.
     * @param taskMetadata The string representation of the event task without the task type.
     * @param isDone The done status of the task.
     * @return The event task parsed from the string.
     * @throws IllegalArgumentException If the event task string is invalid.
     */
    private static EventTask parseEventTask(String taskMetadata, boolean isDone) {
        String[] parts = taskMetadata.split("\\|", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid event task string: " + taskMetadata);
        }
        String description = parts[0];
        LocalDate eventStartTime = LocalDate.parse(parts[1]);
        LocalDate eventEndTime = LocalDate.parse(parts[2]);
        return new EventTask(description, eventStartTime, eventEndTime, isDone);
    }
}
