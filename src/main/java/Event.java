import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDateTime start;

    private LocalDateTime end;
    public Event (int taskNumber, String taskName, boolean taskCompletionStatus, LocalDateTime start, LocalDateTime end) {
        super(taskNumber, taskName, taskCompletionStatus);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy h.mma")) + ")";
    }

    @Override
    public String storageString() {
        return "[E]" + super.toString() + " /from " + this.start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " /to " + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
