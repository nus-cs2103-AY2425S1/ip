package matcha.task;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + this.from + " | " + this.to;
    }
    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + this.from.format(Task.getOutputFormat()) + " to: " +
                this.to.format(Task.getOutputFormat()) + ")";
    }
}
