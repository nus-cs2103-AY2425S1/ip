package GPT;
/**
 * Represents a ToDo task in the GPT application.
 * A ToDo task is a task that needs to be done but does not have a specific deadline or time frame.
 */
class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description, TaskType.TODO);
    }
}