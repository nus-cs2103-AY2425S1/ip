package park.task;

import park.parser.DTFormatter;

import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;

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
