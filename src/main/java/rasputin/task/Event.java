package rasputin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that takes place during a particular duration.
 * If the event duration given is not the correct format, throws an InvalidTaskException.
 */
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
            String fromStr = fromSplit[0] + "T" + fromSplit[1].substring(0, 2) + ":" +
                    fromSplit[1].substring(2);
            String toStr = toSplit[0] + "T" + toSplit[1].substring(0, 2) + ":" +
                    toSplit[1].substring(2);
            this.fromDateTime = LocalDateTime.parse(fromStr);
            this.toDateTime = LocalDateTime.parse(toStr);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Invalid event duration format.");
        }
    }

    /**
     * Returns the string representation of the start time of the event.
     *
     * @return Start time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the string representation of the end time of the event.
     *
     * @return End time of the event.
     */
    public String getTo() {
        return to;
    }

    @Override
    public String getType() {
        return "Event";
    }

    /**
     * Returns the string as the format to be saved in the .txt file.
     *
     * @return String in the format to be saved
     */
    @Override
    public String toSaveFormat() {
        String str = String.format("E|%s|%s|%s|%s\n", getStatusIdentifier(), description, from, to);
        return str;

    }

    @Override
    public String toString() {
        String fromDate = fromDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        String toDate = toDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
