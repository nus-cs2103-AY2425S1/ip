package duke.tasks;

/**
 * Represents a Todo task in the DailyTasks application.
 * A Todo task is a type of task that only contains a description without any specific date or time.
 */
public class ToDos extends Task {

    /**
     * Creates a new Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, which includes the task type identifier,
     * status icon, and the description of the task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + " [" + this.getStatusIcon() + "] " + "[Priority: " + this.priority + "] " + super.toString();
    }
}
