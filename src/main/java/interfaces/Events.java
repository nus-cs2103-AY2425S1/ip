package interfaces;

/**
 * Represents an event task, which is a specific type of Task with a start and end time.
 * This class extends the Task class and adds functionality specific to events.
 */
public class Events extends Task {
    /**
     * The end time of the event.
     */
    protected String to;

    /**
     * The start time of the event.
     */
    protected String from;

    /**
     * Constructs a new Event task.
     *
     * @param description The description of the event task. The "event " prefix will be removed if present.
     * @param to          The end time of the event.
     * @param from        The start time of the event.
     */
    public Events(String description, String to, String from) {
        super(description.replace("event ", ""));
        this.to = to;
        this.from = from;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string in the format "[E][x] description (from: start-time to end-time)"
     * where x is replaced with " " if the task is not done.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to " + this.to + ")";
    }

    /**
     * Returns a string representation of the Event task suitable for saving to a file.
     *
     * @return A string in the format "event description /from start-time /to end-time | isDone | tag"
     */
    @Override
    public String loadString() {
        return "event " + this.description + "/from " + this.from + "/to " + this.to + " | " + this.isDone + " | " + this.tag;
    }
}