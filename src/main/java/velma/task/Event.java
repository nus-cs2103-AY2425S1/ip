package velma.task;

/**
 * Event class - event task that has starting time and end time
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Constructor for event class
     * @param description
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

}
