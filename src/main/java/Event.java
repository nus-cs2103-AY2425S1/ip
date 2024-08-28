import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        String[] fromSplit = from.split(" ");
        String[] toSplit = to.split(" ");
        try {
            String fromStr = fromSplit[0] + "T" + fromSplit[1].substring(0, 2) + ":" + fromSplit[1].substring(2);
            String toStr = toSplit[0] + "T" + toSplit[1].substring(0, 2) + ":" + toSplit[1].substring(2);
            this.fromDateTime = LocalDateTime.parse(fromStr);
            this.toDateTime = LocalDateTime.parse(toStr);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Invalid event duration format.");
        }
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getType() {
        return "Event";
    }

    @Override
    public String toString() {
        String fromDate = fromDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        String toDate = toDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
