package fence.task;

/**
 * Represents an event task which contains a description, start time and end time.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs an event task with the specified description, start time and end time.
     * @param description Description of the task.
     * @param from Start time of the task.
     * @param to End time of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of this event task with the task type, completion status, description, start
     * time and end time.
     * @return String representation of this event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns the string representation within the storage file of this event task with the task type, completion
     * status, description, start time and end time.
     * @return String representation within the storage file of this event task.
     */
    @Override
    public String toTxt() {
        return "EVENT " + super.toTxt() + " /from " + this.from + " /to " + this.to;
    }

    @Override
    public boolean isDue() {
        return false;
    }
}
