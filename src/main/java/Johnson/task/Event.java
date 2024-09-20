package Johnson.task;

/**
 * Represents an event, which includes a date and optional time.
 */
public class Event extends DatedTask {
    /**
     * Constructs an Event with the specified task name, date, and optional tags.
     *
     * @param event the name of the event.
     * @param date the date of the event.
     * @param tags the tags of the event.
     */
    public Event(String event, String date, String... tags) {
        super(event, date, tags);
    }

    public Event() {
        super();
    }

    /**
     * Constructs an Event with the specified task name, date, time, and optional tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param time the time of the task.
     * @param tags the tags of the task.
     */
    public Event(String task, String date, String time, String... tags) {
        super(task, date, time, tags);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDate().toString() + " " + (this.getTime() == null ? "" : this.getTime()) + ")";
    }
}
