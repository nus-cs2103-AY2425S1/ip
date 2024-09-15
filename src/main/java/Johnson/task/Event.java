package Johnson.task;

/**
 * Represents an event, which includes a date and optional time.
 */
public class Event extends DatedTask {
    public Event(String event, String date, String... tags) {
        super(event, date, tags);
    }

    public Event() {
        super();
    }

    public Event(String task, String date, String time, String... tags) {
        super(task, date, time, tags);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate().toString() + " " + (this.getTime() == null ? "" : this.getTime()) + ")";
    }
}
