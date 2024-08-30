import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    protected LocalDateTime deadline;

    public Deadlines(String description, LocalDateTime deadline) {
        super(description + " (by: " + formatDate(deadline) + ")", TaskType.DEADLINE);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s", isDone ? 1 : 0, description);
    }
}
