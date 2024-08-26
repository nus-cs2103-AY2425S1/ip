import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    private LocalDateTime from;
    private LocalDateTime to;

    public Event (String name, boolean done, String from, String to) throws TarsException{
        super(name, done);
        try {
            this.from = DateTimeParser.parse(from);
            this.to = DateTimeParser.parse(to);
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }

    }

    public void setFrom(String newFrom) throws TarsException {
        try {
            this.from = DateTimeParser.parse(newFrom);
        }catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    public void setTo(String newTo) throws TarsException {
        try {
            this.to = DateTimeParser.parse(newTo);
        } catch (DateTimeParseException e) {
            throw new TarsException("Invalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }

    public String getFrom() {
        return DateTimeParser.format(this.from);
    }

    public String getTo() {
        return DateTimeParser.format(this.to);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getFrom() + " to: " + getTo() + ")";
    }
}
