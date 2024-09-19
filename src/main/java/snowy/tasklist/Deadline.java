package snowy.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class is a task that has a specific time frame.
 *
 * The Deadline class extends the Task class and adds a deadline.
 * Deadline has a description and a deadline ("by").
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
        return "[D]" + super.toString() + "(by: " + formattedDate + ")" + this.getTags();
    }
}
