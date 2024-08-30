package event;
import task.Task;

/**
 * Represents a task with a start and end time. An event task has a name and a start date/time and an end date/time.
 * <p>
 * Inherits from the {@link Task} class.
 * </p>
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructs a {@code Event} task with the specified name, start date/time and end date/time.
     *
     * @param name The name of the task.
     * @param startTime The date/time at which the event starts.
     * @param endTime The date/time at which the event ends.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String str = "[E]";
        if (super.isDone()) {
            str += "[X]";
        } else {
            str += "[ ]";
        }
        str += (" " + super.getName() + " "
                + "(from: " + this.startTime + " "
                + "to: " + this.endTime
                + ")\n");
        return str;
    }

    /**
     * Returns the start date and time of the event.
     *
     * @return The start date and time of the event.
     */
    public String getFrom() {
        return this.startTime;
    }

    /**
     * Returns the end date and time of the event.
     *
     * @return The end date and time of the event.
     */
    public String getTo() {
        return this.endTime;
    }
}
