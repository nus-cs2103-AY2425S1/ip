import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        // Use a more human-readable date format for display
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(formatter) + ")";
    }

    private LocalDateTime parseDateTime(String dateTime) {
        // Simplified parse logic (ensure this matches how you input dates)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            return LocalDateTime.now(); // Default to now if parsing fails (improve this as needed)
        }
    }
}
