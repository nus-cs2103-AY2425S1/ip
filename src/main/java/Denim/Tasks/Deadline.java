package denim.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + deadline.format(dateFormatter) + ")";
    }

    @Override
    public String toSimplifiedString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedString = String.format("D | %d | %s | %s\n", super.getIsDone() ? 1 : 0,
                super.getDescription(), deadline.format(dateFormatter));
        return formattedString;
    }
}