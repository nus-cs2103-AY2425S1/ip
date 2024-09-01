import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Event extends Task {

    private String eventStartStr;
    private String eventEndStr;
    private TemporalAccessor eventStart;
    private TemporalAccessor eventEnd;

    public Event(String title, TemporalAccessor start, TemporalAccessor end, boolean completed) {
        super(title, completed);
        this.eventStart = start;
        this.eventEnd = end;
    }

    public Event(String title, TemporalAccessor start, TemporalAccessor end) {
        super(title);
        this.eventStart = start;
        this.eventEnd = end;
    }

    /**
     * Constructs a new 'Event' object which can be added to Tayoo's list. Event is defined as a task
     * with a start and end date/time
     *
     * @param title title of the event
     * @param start start date/time of the event
     * @param end end date/time of the event
     */
    public Event(String title, String start, String end, boolean completed) {
        super(title, completed);
        this.eventStartStr = start;
        this.eventEndStr = end;
    }

    /**
     * Constructs a new 'Event' object which can be added to Tayoo's list. Event is defined as a task
     * with a start and end date/time. Assumes new Event is not completed.
     *
     * @param title title of the event
     * @param start start date/time of the event
     * @param end end date/time of the event
     */
    public Event(String title, String start, String end) {
        super(title);
        this.eventStartStr = start;
        this.eventEndStr = end;
    }

    @Override
    public String toString() {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        if (eventStart != null && eventEnd != null) {
            return "[E]" + super.toString() + " (from: " + dateFormatter.format(eventStart) + " to: "
                    + dateFormatter.format(eventEnd) + " )";
        }


        return "[E]" + super.toString() + " (from: " + eventStartStr + " to: " + eventEndStr + " )";
    }

    /**
     * Returns the Event in a form that can be stored as data in a .txt file
     * Tasks will be stored in the format "Event" | [true OR false] | [Event title] | [start] | [end]. The value true
     * will be stored if the Event is completed, and false otherwise.
     *
     * @return string representation of Event in command form
     */
    public String toTxt() {
        if (this.isCompleted()) {
            return "Event | true | " + this.getTitle() + " | " + this.eventStartStr + " | " + this.eventEndStr;
        } else {
            return "Event | false | " + this.getTitle() + " | " + this.eventStartStr + " | " + this.eventEndStr;
        }
    }
}
