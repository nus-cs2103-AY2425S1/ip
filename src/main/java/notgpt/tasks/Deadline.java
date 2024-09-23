package notgpt.tasks;
import notgpt.parsers.DateParser;

/**
 * Represents a task that has a deadline by which it must be completed.
 * <p>
 * A deadline task includes a description and a specific "by" time, which
 * indicates when the task needs to be completed.
 * </p>
 */
public class Deadline extends Task {
    private String by;
    private DateParser dateParser = new DateParser();

    /**
     * Constructs a {@code Deadline} object from a string input.
     * <p>
     * The input string is expected to contain a task description followed by
     * a "/by" keyword and the deadline time.
     * </p>
     *
     * @param s the string containing the task description and deadline time
     * @throws IllegalArgumentException if the input string is missing either the task description
     *                                  or the "by" time, or if either part is empty.
     */
    public Deadline(String s) {
        super(s.split(" /by ")[0]);
        String[] parts = s.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Deadlines must include both a task description "
                    + "and a '/by' time and cannot include extra \"/\"...");
        }
        this.by = parts[1].trim();
        String toDate = dateParser.giveDate(by);
        if (toDate != null) {
            this.by = toDate;
        }
    }

    /**
     * Returns a string representation of the deadline task, including its type, description,
     * and the deadline by which it must be completed.
     *
     * @return a string in the format "[D] description (by: deadline)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
