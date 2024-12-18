package barry.task;

/**
 * Represents an event task given by the user.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specific description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task in a format to save the file.
     * The format consists of the type of task (ie E for event), and the task's completion status,
     * followed by the task description, start time and end time.
     *
     * @return A String representing the event task in save format.
     */
    @Override
    public String saveFormat() {
        return "E | " + super.saveFormat() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
