import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private LocalDateTime deadline;

    public static String format = "deadline <description> /by <date>";

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + ")";
    }
}
