package velma.task;

/**
 * After class - Represents a type of task that occurs after another event
 */
public class After extends Task {
    protected String after;

    /**
     * Constructor for after
     * @param description
     * @param after
     */
    public After(String description, String after) {
        super(description);
        this.after = after;
    }

    @Override
    public String toString() {
        return "[A]" + super.toString() + " (after: " + after + ")";
    }

    public String getAfter() {
        return after;
    }
}
