import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        this.by = DateUtils.parseDateTime(by);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")) + ")";
    }

    @Override
    public String toFileFormat() {
        String task = "D";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description + " | " + this.by.format(DateUtils.DATE_TIME_FORMATTER);
    }
}
