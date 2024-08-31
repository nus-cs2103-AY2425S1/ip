package nuffle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        // Constructor for Deadline class
        super(description);
        this.by = by;
    }

    public String getDescription() {
        return description;
    }

    public String getFormattedDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        // Add a [D] at the front of task description (parent class)
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }

    public String saveFormat() {
        String temp;
        if (isDone) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "D | " + temp + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HHmm"));
    }
}
