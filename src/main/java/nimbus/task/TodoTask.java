package nimbus.task;

/**
 * Tasks that do not have a deadline
 */
public class TodoTask extends Task {
    private String taskName;

    /**
     * Creates a TodoTask with default incomplete status
     *
     * @param taskName
     */
    public TodoTask(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    /**
     * Creates a TodoTask with default completed status
     *
     * @param taskName
     * @param isCompleted
     */
    public TodoTask(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
        this.taskName = taskName;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (super.isComplete() ? "1" : "0") + " | "
                + taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
