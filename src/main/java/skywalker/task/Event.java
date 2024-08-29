package skywalker.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    public LocalDateTime from;
    public LocalDateTime to;


    /**
     * Constructs an event with description, from time, and end time
     * @param description The Description of the event
     * @param from start time
     * @param to end time
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
