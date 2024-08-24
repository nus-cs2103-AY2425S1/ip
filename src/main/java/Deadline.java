import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task implements Datable{
    private LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + getFileDateString(by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + getDisplayDateString(by) + ")";
    }
}
