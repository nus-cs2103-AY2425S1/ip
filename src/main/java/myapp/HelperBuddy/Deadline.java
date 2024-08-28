package myapp.HelperBuddy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime deadlineBy;

    public Deadline(String description, LocalDateTime deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D][" + (this.getDone() ? "X" : " ") + "] " + this.getDescription() +
                (deadlineBy != null ? " (by: " + deadlineBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")" : "");
    }

    @Override
    public String toFileFormat() {
        return "D | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription() +
                (deadlineBy != null ? " | " + deadlineBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) : "");
    }

    public static Deadline parseTask(String taskData) {
        String[] parts = taskData.split(" \\| ");
        String description = parts[2].trim();
        LocalDateTime deadlineBy = null;
        if (parts.length > 3) {
            try {
                deadlineBy = LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            } catch (DateTimeParseException e) {
                System.out.println("Warning: There is no date format provided");
            }
        }
        Deadline deadline = new Deadline(description, deadlineBy);
        if (parts[1].trim().equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }
}
