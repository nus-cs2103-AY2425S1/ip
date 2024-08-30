import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description + " (from: " + formatDate(start) + ", to: " + formatDate(end) + ")", TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s", isDone ? 1 : 0, description);
    }

    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }
}
