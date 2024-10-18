package velma.task;

/**
 * After task class.
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


}
