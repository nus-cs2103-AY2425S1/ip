package task;

/**
 * Represents a task with a description and a boolean to indicate if it is done.
 */
public class Task {
    private final TaskType taskType;
    private final String description;
    private boolean isDone;

    /**
     * Constructor for task.Task class.
     *
     * @param description Description of the task.
     */
    public Task(TaskType taskType, String description) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Serializes the task to a string.
     *
     * @return Serialized task.
     */
    public String serialize() {
        return taskType.toString().charAt(0) + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Deserializes the task from a string.
     *
     * @param line The string to deserialize.
     * @return The deserialized task.
     */
    public static Task deserialize(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = switch (type) {
        case "T" -> new ToDo(description);
        case "D" -> {
            String deadline = parts[3];
            yield new Deadline(description, deadline);
        }
        case "E" -> {
            String startDateTime = parts[3];
            String endDateTime = parts[4];
            yield new Event(description, startDateTime, endDateTime);
        }
        default -> throw new IllegalArgumentException("Unknown task type: " + type);
        };

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + taskType.toString().charAt(0) + "][" + getStatusIcon() + "] " + description;
    }
}
