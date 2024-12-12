package barcus.task;

/**
 * Task with description only
 */
public class Todo extends Task {

    /**
     * Constructor
     * @param description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor
     * @param description of task
     * @param isDone status of task
     */
    public Todo(String description, boolean isDone, String tags) {
        super(description, isDone, tags);
    }

    /**
     * Converts to save file friendly string
     * @return string
     */
    @Override
    public String convertToSavedString() {
        return "T | " + super.convertToSavedString();
    }

    /**
     * Returns string representation
     * @return string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
