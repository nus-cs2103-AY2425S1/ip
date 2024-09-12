package task;

/**
 * Represent a task that is marked as TODO
 */
public class ToDo extends Task {
    /**
     * Constructor for the ToDo
     * 
     * @param description description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}