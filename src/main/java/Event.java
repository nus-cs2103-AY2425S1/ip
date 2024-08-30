import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = Task.dateTimeParser(from);
        this.to = Task.dateTimeParser(to);
    }
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        String from = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
        String to = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));

        if(from.endsWith("12:00am")) {
            from = from.substring(0, from.length() - 8);
        }

        if(to.endsWith("12:00am")) {
            to = to.substring(0, to.length() - 8);
        }
        return String.format("[E] [%s] %s (from: %s to: %s)", mark, super.description, from, to);
    }
}
