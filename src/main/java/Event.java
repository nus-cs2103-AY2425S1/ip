import java.time.LocalDate;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

//    public Event(String description, String from, String to, boolean isDone) {
//        super(description);
//        this.from = from;
//        this.to = to;
//        this.isDone = isDone;
//    }

    @Override
    public String serialize() {
        return String.format("E|%b|%s|%s|%s", this.isDone, this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        String toStr = this.to.equals(LocalDate.MAX) ? "" : " to: " + this.to;
        return String.format("[E]%s (from: %s%s)", super.toString(), this.from, toStr);
    }
}
