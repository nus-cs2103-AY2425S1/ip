package hana.task;

/**
 * Represents a ToDo task with a description and deadline.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with description and deadline.
     *
     * @param description The description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the details of the task.
     *
     * @return The details of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the details of the task to save as.
     *
     * @return The details of the task to save as.
     */
    @Override
    public String fileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + priority + " | " + description;
    }
}
