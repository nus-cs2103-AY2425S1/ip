package evan.task;

/**
 * Represents a Deadline task that the chatbot stores.
 */
public class Deadline extends Task {
    protected final DateTime by;

    /**
     * Instantiates a Deadline object with the given description and deadline.
     *
     * @param description Description of the Deadline.
     * @param by When the Deadline is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateTime.parseInput(by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encodeAsString() {
        return String.format("D | %s | %s | %s", (this.isDone ? "1" : "0"), description, by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}