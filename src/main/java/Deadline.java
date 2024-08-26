import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDateTime getDate() {
        return deadline;
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + deadline.format(outputDateFormat);
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + deadline.format(fileDateFormat);

    }
}
