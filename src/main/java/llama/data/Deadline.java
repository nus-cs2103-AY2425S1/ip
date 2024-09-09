package llama.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline (endTime)
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime endTime;

    /**
     * Creates a Deadline object
     *
     * @param desc description of deadline to be done
     * @param endTime date and time that deadline needs to be done by
     * @param isDone true if deadline is done; else false
     */
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
