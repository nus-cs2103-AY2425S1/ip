package bottle.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Deadline.
 */
public class Deadline extends Task {
    /**
     * The By.
     */
    protected LocalDateTime by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description
     * @param by          the by
     */
    public Deadline(String description, LocalDateTime by) {
            super(description);
            this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "D | " + (isChecked ? "1 | " : "0 | ") + this.taskDesc + " | " + this.by.format(formatter);
    }
}
