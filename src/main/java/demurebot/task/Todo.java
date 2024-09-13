package demurebot.task;

/**
 * Represents a Todo task in the DemureBot application.
 * A Todo task has a description and a status indicating whether it is done.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description and status.
     *
     * @param description The description of the Todo task.
     * @param isDone The status of the Todo task, true if the task is done, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

