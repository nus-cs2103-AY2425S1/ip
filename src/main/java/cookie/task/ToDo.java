package cookie.task;

/**
 * Represents a to-do task in the task list.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description the description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new {@code ToDo} task with the specified completion status and description.
     *
     * @param isDone a boolean indicating whether the task is completed
     * @param description the description of the to-do task
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
