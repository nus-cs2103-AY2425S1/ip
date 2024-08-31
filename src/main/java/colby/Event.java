package colby;

/**
 * Class that represents event tasks
 * description refers to what the task is about
 * start and end refer to the start time/date and end time/date of the event
 */
public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string depiction of the task with the type shown as "[E]", followed by whether the
     * task is marked as done or not, the description of the task, and the start and end time/date
     * @return string of the event task with its details
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + "to: " + end + ")";
    }
}
