package taskalyn;

/**
 * Represents a Todo Task.
 */
public class TodoTask extends Task {

    /**
     * Constructs the TodoTask with description and completion status.
     *
     * @param taskItem Description of task.
     * @param isCompleted Whether task has been completed or not.
     */
    public TodoTask(String taskItem, boolean isCompleted) {
        super(taskItem, isCompleted);
    }

    /**
     * Returns a String expression of the TodoTask.
     *
     * @return String expression of TodoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String expression used in database file.
     *
     * @return String expression used in database file.
     */
    @Override
    public String toDatabaseFormat() {
        return "T | " + (this.isCompleted() ? "1" : "0") + " | " + this.getTaskDescription();
    }
}
