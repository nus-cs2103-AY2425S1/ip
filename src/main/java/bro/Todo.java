package bro;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task for saving/loading purposes.
     * The string includes the task type and status.
     *
     * @return A string representing the Todo task, formatted for storage.
     *         Format: [T][status] description
     */
    @Override
    public String toLoad() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task for display purposes.
     * The string includes the task type and status.
     *
     * @return A string representing the Todo task, formatted for display.
     *         Format: [T][status] description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

