package llama.data;

/**
 * Represents a task to be done
 */
public class Todo extends Task {
    /**
     * Creates a Todo object
     *
     * @param description description for todo to be done
     * @param isDone true if todo is done; else false
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
