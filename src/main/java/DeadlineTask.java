import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    protected LocalDateTime deadline;

    // Constructor accepting a LocalDateTime object directly
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    // Constructor that accepts a String and parses it
    public DeadlineTask(String description, String deadline) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String formattedDeadline = deadline.format(formatter);
        return "[D] " + super.toString() + " (by: " + formattedDeadline + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        String formattedDeadline = deadline.format(formatter);
        return "D | " + getStatusIcon() + " | " + getDescription() + " | " + formattedDeadline;
    }
}
