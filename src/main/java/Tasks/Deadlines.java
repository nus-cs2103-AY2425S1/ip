package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks with an end time condition.
 */
public class Deadlines extends Task {
    private LocalDateTime deadlineInfo;
    private static final DateTimeFormatter PATTERN_DISPLAY = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter PATTERN_READ = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadlines(String name, String details) {
        super(name);
        this.deadlineInfo = LocalDateTime.parse(details, PATTERN_READ);
    }

    /**
     * Returns deadline end period as a writeable string.
     */
    @Override
    public String serialize() {
        return this.deadlineInfo.format(PATTERN_READ);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.deadlineInfo.format(PATTERN_DISPLAY);
    }
}
