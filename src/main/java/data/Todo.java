package data;

/**
 * Represents a task to be done
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description description for task to be done
     * @param isDone true if task is done; else false
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getSaveTaskString() {
        return "T" + super.getSaveTaskString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
