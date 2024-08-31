package park.task;

import park.parser.DTFormatter;

import java.time.format.DateTimeParseException;

/**
 * Represents a Task with a deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = DTFormatter.format(by);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("please input DateTime in format yyyy-MM-dd HHmm", e.getParsedString(), e.getErrorIndex());
        }
    }

    @Override
    public String encode() {
        return "D/" + this.getStatusIcon() + "/" + description + "/" + by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
