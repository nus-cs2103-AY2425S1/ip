package lutodo.tasks;

/**
 * Represents a to-do task.
 */
public class TodoTask extends Task{

    /** Constructs a to-do task with description.
     *
     * @param description Description of the task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns The message containing basic information of this task.
     * This task: Type of task: To-do, whether it is done and description.
     *
     * @return The message containing basic information of this task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + super.description;
    }

}
