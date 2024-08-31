package evan.task;

/**
 * Represents a Todo task that the chatbot stores.
 */
public class Todo extends Task {
    /**
     * Instantiates a Todo object with the given description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encodeAsString() {
        return String.format("T | %s | %s", (this.isDone ? "1" : "0"), description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}