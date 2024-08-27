import java.time.LocalDateTime;

public class Event extends Task{
    protected String from;
    protected String to;

    Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    Event(String description, boolean isDone , String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFromDate() {
        return this.from;
    }

    public String getToDate() {
        return this.to;
    }

    @Override
    public String toFileFormat() {
        String isDoneString = this.isDone ? "1" : "0";
        return getTypeIcon().charAt(1) + "|" + isDoneString + "|" +
                this.getDescription() + "|" + this.getFromDate() + "|" + this.getToDate();
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
