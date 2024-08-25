import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter SAVE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String name, int number, LocalDateTime deadline) {
        super(name, number);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DISPLAY_FORMATTER) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (completed ? 1 : 0) + " | " + name + " | " + deadline.format(SAVE_FORMATTER);
    }

    public static Deadline parseFromString(String name, int number, String deadlineString) {
        LocalDateTime deadline = LocalDateTime.parse(deadlineString, SAVE_FORMATTER);
        return new Deadline(name, number, deadline);
    }
}
