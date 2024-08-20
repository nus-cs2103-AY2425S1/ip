import java.time.LocalDateTime;

public class Event extends Task{
    protected String from;
    protected String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.toString()
               + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
    }
}
