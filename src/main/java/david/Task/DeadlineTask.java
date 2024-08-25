package david.Task;

import david.Parser.DateParser;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime by;

    public DeadlineTask(String eventName, LocalDateTime by, boolean completed) {
        super(eventName, completed);
        this.by = by;
    }
    @Override
    public String toCacheString() {
        return "D|" + (this.isCompleted() ? "1" : "0") + "|"
                + this.getTask() + "|" + DateParser.formatCacheDate(this.by);
    }
    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[D]" + "[" + isCompleted + "] " + super.getTask() + "(by: " + DateParser.formatOutputDate(this.by) + ")";
    }
}
