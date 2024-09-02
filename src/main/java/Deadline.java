import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    Deadline(String description, String deadline) {
        this(description, Converter.InputToDateTime(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Converter.DateTimeToOutput(deadline) + ")";
    }
}
