package Johnson.task;

/**
 * Represents an event, which includes a date and optional time.
 */
public class Event extends DatedTask {
    public Event(String event, String date) {
        super(event, date);
    }

    public Event() {
        super();
    }

    public Event(String task, String date, String time) {
        super(task, date, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate().toString() + " " + this.getTime() + ")";
    }
}
