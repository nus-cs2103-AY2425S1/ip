package nextgpt.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Class for tasks with type deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            return super.equals(o) && ((Deadline) o).by.equals(this.by);
        }
        return false;
    }
}
