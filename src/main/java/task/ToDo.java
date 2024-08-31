package task;

/**
 * Represents a to-do task in the Friday application.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the specified task name that is not completed by default.
     *
     * @param taskName The task name.
     */
    public ToDo(String taskName){
        super(taskName);
    }

    /**
     * Constructs a to-do task with the specified task name and completion status.
     *
     * @param taskName The task name.
     * @param isCompleted The completion status.
     */
    public ToDo(String taskName, boolean isCompleted){
        super(taskName, isCompleted);

    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        return "T " + (super.isCompleted() ? "0" : "1") + " " + this.getTaskName();
    }
}
