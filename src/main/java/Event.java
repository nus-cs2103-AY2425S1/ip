import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime  from;
    private final LocalDateTime to;
    public Event(String description, LocalDateTime  from, LocalDateTime  to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isComplete, LocalDateTime  from, LocalDateTime  to) {
        super(description, isComplete);
        this.from = from;
        this.to = to;
    }

    public String toSaveFormat() {
        return String.format("E | %s | %s | %s",
                super.toSaveFormat(),
                DateUtils.toOutputString(from),
                DateUtils.toOutputString(to));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DateUtils.toOutputString(from),
                DateUtils.toOutputString(to));
    }
}
