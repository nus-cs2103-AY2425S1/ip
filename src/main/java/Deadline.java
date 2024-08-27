import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime endTime;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String desc, LocalDateTime endTime, boolean isDone) {
        super(desc, isDone);
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "D" + super.getSaveTaskString() + "|" + this.endTime.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.endTime.format(FORMATTER) + ")";
    }
}
