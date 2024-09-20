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
    public Task edit(TaskField field, String newString) {
        if (field != TaskField.DESCRIPTION) {
            throw new IllegalArgumentException("You can only edit a todo task with /description");
        }

        setDescription(newString);
        return this;
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
