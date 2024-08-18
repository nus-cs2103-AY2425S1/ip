public class Deadline extends Task {
    /**
     * The due date/time of this deadline.
     */
    protected String dueDateTime;
    /**
     * The constructor.
     * @param description Deadline description.
     */
    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    /**
     * Get the due date/time.
     * @return Due date/time.
     */
    public String getDueDateTime() {
        return dueDateTime;
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return A string representation of this deadline.
     */
    @Override
    public String toString() {
        return "[DDL]" + super.toString();
    }
}
