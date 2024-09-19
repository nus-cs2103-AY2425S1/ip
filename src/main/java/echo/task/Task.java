package echo.task;

/**
 * The Task class represents a basic task with a description and completion status.
 */
public class Task {
    private Boolean isComplete = false;
    private String description;
    private TaskType type;
    /**
     * Constructs a Task with the specified description and type.
     *
     * @param description the description of the task
     * @param type the type of the task (TODO, EVENT, DEADLINE)
     */
    public Task(String description, TaskType type) {
        assert !description.isEmpty(): "Description should not be empty";
        assert type != null: "Task type should not be null";
        this.description = description;
        this.type = type;
    }
    /**
     * Returns a formatted string representing the task,
     * including its type, completion status, and description.
     *
     * @return a string representing the task
     */
    public String getTaskString() {
        String msg = "[" + this.type.getTypeSymbol() + "] ";
        msg += "[";
        if (isComplete) {
            msg += "X] ";
        } else {
            msg += " ] ";
        }
        msg += this.description;
        return msg;
    }
    /**
     * Marks the task as complete.
     */
    protected void completeTask() {
        this.isComplete = true;
    }
    /**
     * Unmarks the task, indicating it is not complete.
     */

    protected void uncompleteTask() {
        this.isComplete = false;
    }
    protected Boolean getIsComplete() {
        return this.isComplete;
    }
    /**
     * Returns a string representing the task's data,
     * suitable for saving to a file.
     *
     * @return a string representing the task's data
     */
    public String getData() {
        return type.getTypeSymbol() + " | " +
                (isComplete ? 1 : 0) + " | " +
                description;
    }
    public String[] getTempStrings() {
        return new String[] {this.description, "", "", ""};
    }
    public TaskType getTaskType() {
        return this.type;
    }
}
