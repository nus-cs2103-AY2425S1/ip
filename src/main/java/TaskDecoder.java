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
        String[] taskParts = taskString.split(" | ", 2);
        if (taskParts.length < 2) {
            throw new IllegalArgumentException("Invalid task string: " + taskString);
        }
        String taskType = taskParts[0];
        String taskMetadata = taskParts[1];

        switch (taskType) {
            case "T":
                // Metadata for todo tasks is just the description
                return new TodoTask(taskMetadata);
            case "D":
                return parseDeadlineTask(taskMetadata);
            case "E":
                return parseEventTask(taskMetadata);
            default:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }

    /**
     * Parses a deadline task from the specified string.
     * Deadline task contains a description and a deadline.
     * @param taskMetadata The string representation of the deadline task without the task type.
     * @return The deadline task parsed from the string.
     * @throws IllegalArgumentException If the deadline task string is invalid.
     */
    private static DeadlineTask parseDeadlineTask(String taskMetadata) {
        String[] parts = taskMetadata.split(" | ", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid deadline task string: " + taskMetadata);
        }
        String description = parts[0];
        String deadline = parts[1];
        return new DeadlineTask(description, deadline);
    }

    /**
     * Parses an event task from the specified string.
     * Event task contains a description, an event start time, and an event end time.
     * @param taskMetadata The string representation of the event task without the task type.
     * @return The event task parsed from the string.
     * @throws IllegalArgumentException If the event task string is invalid.
     */
    private static EventTask parseEventTask(String taskMetadata) {
        String[] parts = taskMetadata.split(" | ", 3);
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid event task string: " + taskMetadata);
        }
        String description = parts[0];
        String eventStartTime = parts[1];
        String eventEndTime = parts[2];
        return new EventTask(description, eventStartTime, eventEndTime);
    }
}
