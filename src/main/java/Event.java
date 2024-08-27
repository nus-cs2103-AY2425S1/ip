import java.time.LocalDate;

public class Event extends Task{
    protected String from;
    protected String to;
//    protected LocalDate fromTime;
//    protected LocalDate toTime;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
        this.from = InputParser.checkDateFormat(from);
        this.to = InputParser.checkDateFormat(to);
    }

    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + desc + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
