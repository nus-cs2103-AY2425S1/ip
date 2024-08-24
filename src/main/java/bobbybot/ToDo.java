package bobbybot;

/**
 * Represents a to-do task.
 * A to-do task has a description.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", getTaskType(), super.toString());
    }

    @Override
    public String getFileString() {
        return getTaskType() + " | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}