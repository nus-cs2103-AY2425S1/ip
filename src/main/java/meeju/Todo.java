package meeju;

/**
 * Represents a todo task.
 * This class extends the <code>Task</code> class.
 */
public class Todo extends Task {
    private static final String TASK_ICON = "[T]";
    private final String taskType;

    /**
     * Constructor for a todo task.
     *
     * @param taskDescription The description of the task.
     */
    public Todo(String taskDescription) {
        super(taskDescription, false);
        this.taskType = "T";
    }

    /**
     * Serializes the task details into a string format suitable for storage.
     * The delimiter used is "!-".
     *
     * @return A string representing the serialized details of the task.
     */
    public String serializeDetails() {
        String fileDelimiter = "!-";
        String taskCharacter = "T";
        return taskCharacter + fileDelimiter
                + this.getIsDone() + fileDelimiter
                + this.getTaskDescription() + "\n";
    }

    public String getTaskIdentifier() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString();
    }
}
