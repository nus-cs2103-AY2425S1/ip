package seedu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task which keeps track of description and end date
 */
public class Deadline extends Task {
    private LocalDate end;

    public Deadline(String description, String end) {
        super(description);
        this.end = LocalDate.parse(end);
    }

    /**
     * Converts Deadline object into a string
     * @return String to be output by Bob when listing.
     */
    @Override
    public String toString() {
        String ending = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + ending + ")";
    }

    /**
     * Converts Deadline object into a string to be saved in the txt file.
     * @return String to be saved in txt file.
     */
    @Override
    public String toSave() {
        return "D" + " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | " + this.end;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Deadline) {
            Deadline t = (Deadline) obj;
            if (this.description == null || t.description == null) {
                return false;
            }
            if (this.end == null || t.end == null) {
                return false;
            }

            return this.description.equals(t.description) && this.end.equals(t.end);
        }

        return false;
    }
}
