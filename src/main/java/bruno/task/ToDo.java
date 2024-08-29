package bruno.task;

/**
 * Represents a ToDo task. A ToDo task is a task with only a description and no date or time constraints.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param done The completion status of the task (true if completed, false otherwise).
     */
    public ToDo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "T | " + super.toString();
    }
}
