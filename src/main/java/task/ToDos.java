package task;

/**
 * Represents a todo task.
 * A todo task is a task that only has a description and no specific date or time associated with it.
 */
public class ToDos extends Task {

    /**
     * Constructs a ToDos task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDos(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns the task type of this task.
     *
     * @return A string representing the task type, which is "T" for todo tasks.
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}