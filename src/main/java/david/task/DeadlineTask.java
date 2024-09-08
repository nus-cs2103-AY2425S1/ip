package david.task;

import java.time.LocalDateTime;

import david.parser.DateParser;

/**
 * Deadline task class
 */
public class DeadlineTask extends Task {
    private LocalDateTime by;

    /**
     * Contructor for Deadline task
     * @param eventName String event name
     * @param by LocalDateTime date
     * @param isCompleted
     */
    public DeadlineTask(String eventName, LocalDateTime by, boolean isCompleted) {
        super(eventName, isCompleted);
        this.by = by;
    }
    @Override
    public String toCacheString() {
        String isCompleted = this.isCompleted() ? "1" : "0";

        return "D|" + isCompleted + "|"
                + this.getTask() + "|" + DateParser.formatCacheDate(this.by);
    }
    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";

        return "[D]" + "[" + isCompleted + "] " + super.getTask() + "(by: "
                + DateParser.formatOutputDate(this.by) + ")";
    }
}
