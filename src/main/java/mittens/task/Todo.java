package mittens.task;

/**
 * Represents a Todo task, that is a task with description only.
 */
public class Todo extends Task {
    
    /**
     * Creates a Todo task with the given description.
     * 
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
