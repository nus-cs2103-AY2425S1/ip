package Azir;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a child of the Task class to indicate a task that has a deadline day
 */
public class Deadline extends Task {
    private LocalDate day;
    public Deadline(String description, LocalDate day) {
        super(description);
        this.day = day;
    }

    /**
     * Formats deadline task into a special string format
     * @return Special string format
     */
    public String formatText() {
        return String.format("D | %s | %s | %s", super.getDoneString(), super.getDescription(),
                this.day.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                this.day.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
