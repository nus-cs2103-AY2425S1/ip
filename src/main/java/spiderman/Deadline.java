package spiderman;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a deadline object with the given description and by date.
     * @param description The description of the deadline.
     * @param by The date to complete the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Formats the deadline's description, status and due date and
     * tags the deadline with a "D" to be saved into the txt file.
     * @return A string that contains the deadline's description, status and due date.
     */
    @Override
    public String toTextFormat() {
        return "D|" + (super.isTaskDone() ? "T" : "F") + "|" + super.getDescription() + "|" + this.by;
    }

    /**
     * Converts deadline description, status and due date to a string and
     * also tags the task with a D.
     * @return A string containing the deadline's description, current status and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
