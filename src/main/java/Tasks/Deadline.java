package Tasks;

import Tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dueWhen;

    public Deadline(String description, LocalDateTime dueWhen) {
        super(description);
        this.dueWhen = dueWhen;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        return "D | " + this.getStatusIcon() + " | " + this.description + " (by: " + this.dueWhen.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + this.getStatusIcon() + " | " + this.description + " | " + this.dueWhen.format(formatter);
    }
}
