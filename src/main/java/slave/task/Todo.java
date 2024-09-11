package slave.task;

/**
 * Todo is a child class of Task that contains a description of the task only
 */
public class Todo extends Task {
    /**
     * Creates an incomplete Todo object with the specified parameters
     *
     * @param taskName
     */
    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formatted as such:
     * (Task type),(isCompleted),(Task name)
     *
     * @return the string representation of the task to be written into the save file
     */
    @Override
    public String saveFormat() {
        // no dates involved, returns the same thing as toString()
        return "todo," + isCompleted() + "," + getTask();
    }
}
