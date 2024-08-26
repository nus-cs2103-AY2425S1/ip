import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private static final DateTimeFormatter READ_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DISPLAY_PATTERN = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    Event(String name, String startDetails, String endDetails) {
        super(name);
        this.startPeriod = LocalDateTime.parse(startDetails, READ_PATTERN);
        this.endPeriod = LocalDateTime.parse(endDetails, READ_PATTERN);
    }

    @Override
    public String getWriteTaskInfo() {
        return this.startPeriod.format(READ_PATTERN) + "," + this.endPeriod.format(READ_PATTERN);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + this.startPeriod.format(DISPLAY_PATTERN) + 
                " to: " + this.endPeriod.format(DISPLAY_PATTERN);
    }
}
