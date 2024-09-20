package dipsy.task;

/**
 * Represents a to-do task. A ToDo task has a description and a completion status (done or not done).
 * This class extends the base {@link Task} class and is used for tasks that do not have a specific date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new {@code ToDo} task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the type of the task, which is "ToDo" for this class.
     * This method is used to identify the specific type of task.
     *
     * @return The string "ToDo" indicating the type of task.
     */
    @Override
    protected String getTaskType() {
        return "ToDo";
    }

    /**
     * Returns a string representation of the ToDo task, including its type and description.
     * This method is used to display the task in a human-readable format.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
