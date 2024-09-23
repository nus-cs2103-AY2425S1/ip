package trackie.tasks;

/**
 * Represents a task of type "Todo".
 */
public class Todo extends Task {
    private String type = "T";

    /**
     * Constructs a todo task.
     *
     * @param description the description of the task as supplied by the user.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a todo task with a custom completion status.
     *
     * @param description the description fo the task as supplied by the user.
     * @param status the completion status of the task.
     */
    public Todo(String description, int status) {
        super(description, status);
    }

    /**
     * Retrieves relevant information about the task.
     *
     * @return A String containing the description of the task.
     */
    @Override
    public String toString() {
        return(super.description);
    }

    /**
     * Retrieves the type of the task.
     *
     * @return A String representing the type of the task. In this case, the type is "T".
     */
    @Override
    public String getTaskType() {
        return this.type;
    }
}
