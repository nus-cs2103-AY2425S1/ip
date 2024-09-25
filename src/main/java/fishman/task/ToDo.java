package fishman.task;

/**
 * Represents the to-do task.
 * This class extends the Task class, without adding any additional attributes.
 */
public class ToDo extends Task {
    /**
     * Constructs a new to-do task.
     *
     * @param taskDescription The description of the task.
     */
    public ToDo(String taskDescription, boolean isTaskDone) {
        super(taskDescription, isTaskDone);
        assert taskDescription != null : "Description should not be null";
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
