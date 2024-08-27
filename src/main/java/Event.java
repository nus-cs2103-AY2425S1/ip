import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String desc, LocalDateTime startTime, LocalDateTime endTime, boolean isDone) {
        super(desc, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "E" + super.getSaveTaskString() + "|" +
                this.startTime.format(formatter) + "|" + this.endTime.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + this.startTime.format(formatter) + "|to: " + this.endTime.format(formatter) + ")";
    }
}
