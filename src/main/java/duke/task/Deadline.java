package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    protected final DateTimeFormatter display_format = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");

    private final String DEADLINE_ICON = "[D]";
    private final String DEADLINE = "D";

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String BY_FORMAT = " (by: " + by.format(display_format) + ")";
        return DEADLINE_ICON + super.toString() + BY_FORMAT;
    }

    /**
     * Returns string representation of the task to be saved in the text file.
     * @return String of Deadline
     */
    @Override
    public String toFileString() {
        return DEADLINE + BAR + (isDone ? "1" : "0") + BAR
                + description + BAR + by.format(display_format);
    }
}