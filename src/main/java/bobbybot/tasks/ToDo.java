package bobbybot.tasks;

/**
 * Represents a to-do task.
 * A to-do task has a description.
 */
public class ToDo extends Task {

    public static final String TASK_TYPE = "T";

    /**
     * Creates a new ToDo object.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TASK_TYPE, super.toString());
    }

    @Override
    public String getFileString() {
        return TASK_TYPE + " | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
