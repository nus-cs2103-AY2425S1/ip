package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private static final DateTimeFormatter PATTERN_DISPLAY = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter PATTERN_READ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String name, String startDetails, String endDetails) {
        super(name);
        this.startPeriod = LocalDateTime.parse(startDetails, PATTERN_READ);
        this.endPeriod = LocalDateTime.parse(endDetails, PATTERN_READ);
    }

    /**
     * Returns event start/end period as a writeable string.
     */
    @Override
    public String getWriteTaskInfo() {
        return this.startPeriod.format(PATTERN_READ) + ", " + this.endPeriod.format(PATTERN_READ);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + this.startPeriod.format(PATTERN_DISPLAY)
                + " to: " + this.endPeriod.format(PATTERN_DISPLAY);
    }
}
