package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;
    protected final DateTimeFormatter display_format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(display_format) + ")";
    }

    /**
     * Returns string representation of the task to be saved in the text file.
     * @return String of Deadline
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(display_format);
    }
}