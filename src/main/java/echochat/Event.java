package echochat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String from;
    protected String to;

    protected LocalDateTime dtFrom;
    protected LocalDateTime dtTo;

    public Event (String from, String to, String desc) {
        super('E',desc);
        this.from = from;
        this.to = to;
        this.dtFrom = parseDateTime(from);
        this.dtTo = parseDateTime(to);
    }

    /**
     * Get description of the event.
     * @return Event description, including its starting and ending dates
     */
    @Override
    public String getDesc() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        String fromOut = (dtFrom == null) ? from : dtFrom.format(outputFormatter);
        String toOut = (dtTo == null) ? to : dtTo.format(outputFormatter);
        return super.getDesc() + " (from: " + fromOut + " to: " + toOut + ")";
    }

    /**
     * Get starting date of event.
     * @return String representing start of event
     */
    public String getFrom() {
        return from;
    }

    /**
     * Get ending date of event.
     * @return String representing end of event
     */
    public String getTo() {
        return to;
    }
}
