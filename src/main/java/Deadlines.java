/**
 * Represents a task with a specific deadline.
 */
public class Deadlines extends Task{
    private String end;
    /**
     * Constructs a deadline task with the given description and end time.
     *
     * @param description The description of the task.
     * @param end The end time of the deadline.
     */
    public Deadlines(String description, String end) {
        super(description);
        this.end = end;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string in the format "[D] description (by: end)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end + ")";
    }

}
