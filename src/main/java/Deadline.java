import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime end;
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Deadline(boolean status, String description, LocalDateTime end) {
        super(status, description);
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "D | " + this.getStatus() + " | " + this.getDescription() + " | " + this.end.format(FILE_DATE_TIME_FORMATTER);
    }

    @Override
    public String toUIString() {
        return "Last night, Darth Vader came down from Planet Vulcan and told me that if you don't meet this deadline... he'd melt your brain! So, better get moving!\n";
    }

    @Override
    public String toString() {
        return "[D][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (by: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

}
