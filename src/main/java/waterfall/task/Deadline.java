package waterfall.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = parseDateTime(deadline);
    }

    private LocalDateTime parseDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateString, formatter);
    }

    private String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return deadline.format(formatter);
    }

    private String getBeautifulDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return deadline.format(formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBeautifulDeadline() + ")";
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "D | 1 | " + this.getTitle() + " | " + this.getFormattedDeadline();
        } else {
            return "D | 0 | " + this.getTitle() + " | " + this.getFormattedDeadline();
        }
    }
}
