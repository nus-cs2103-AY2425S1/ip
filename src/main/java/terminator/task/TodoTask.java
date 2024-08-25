package terminator.task;

/**
 * Concrete class representing a 'Todo' item in the task list.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the data representation of the task, plus its description.
     * @example {@code "T 1 destroy aliens"}
     */
    @Override
    public String getDataRepresentation() {
        return super.getDataRepresentation() + this.description;
    }
}
