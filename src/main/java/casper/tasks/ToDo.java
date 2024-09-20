package casper.tasks;

/**
 * Represents a "ToDo" task, which is a type of task that only requires a description.
 * Inherits the basic task functionalities from the {@link Task} class.
 */
public class ToDo extends Task {
    /**
     * Constructs a new {@code ToDo} task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description A brief description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new {@code ToDo} task with the specified description and completion status.
     *
     * @param description A brief description of the task.
     * @param isDone The completion status of the task. {@code true} if the task is completed, {@code false} otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the "ToDo" task, including its status icon and description.
     * The format is "[T][status icon] description", where [status icon] is "X" if the task is done, otherwise a space.
     *
     * @return A string representing the "ToDo" task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the "ToDo" task suitable for saving to a file.
     * The format is "todo | [status] | description", where [status] is 1 if the task is done, otherwise 0.
     *
     * @return A string representing the "ToDo" task in a format suitable for saving.
     */
    @Override
    public String toSaveString() {
        return String.format("todo | %d | %s", super.isDone ? 1 : 0, this.description);
    }
}
