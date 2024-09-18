package fence.task;

/**
 * Represents a todo task which contains a description.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the specified description.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of this todo task with the task type, completion status and description.
     * @return String representation of this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation within the storage file of this todo task with the task type, completion status
     * and description.
     * @return String representation within the storage file of this todo task.
     */
    @Override
    public String toTxt() {
        return "TODO " + super.toTxt();
    }

    @Override
    public boolean isDue() {
        return false;
    }
}
