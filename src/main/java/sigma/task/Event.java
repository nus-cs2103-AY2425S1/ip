package sigma.task;

/**
 * Class for an Event task
 *
 * @author Qiao Yi
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Constructor for an Event task
     * @param name The name of the event
     * @param status The completion status of the event
     * @param startTime The time the event starts
     * @param endTime The time the event ends
     */
    public Event(String name, boolean status, String startTime, String endTime) {
        super(name, status);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a String representation of an event
     * @return A string representing the name and status of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
