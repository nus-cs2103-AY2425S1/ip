package mryapper.task;

/**
 * A task that is to be done.
 */
public class Todo extends Task {

    /**
     * Creates a task that is to be done.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDataString() {
        return String.format("T ||| %d ||| %s\n",
                this.getStatus(), this.getDescription());
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
