/**
 * This class represents an Event with a description, completion status, start time and end time.
 *
 * <p>This class inherits from the Task class It maintains a description
 * of the event, whether the event has been completed or not and its start and end time</p>
 */
public class Event extends Task{

    protected String startTime;

    protected String endTime;


    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Event.
     * @param startTime String start time of Event.
     * @param endTime String end time of Event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new instance of Event.
     *
     * @param description String description of Event.
     * @param isDone Boolean indicating whether Event is done or not.
     * @param startTime String start time of Event.
     * @param endTime String end time of Event.
     */
    public Event(String description, Boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /**
     * Gets start time of the Event.
     *
     * @return description startTime which indicates when the event is starting.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Gets end time of the Event.
     *
     * @return description endTime which indicates when the event is ending.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the Event.
     *
     * <p>The string includes the E identifier, status icon and the description of the Event.</p>
     *
     * @return A string in the format {" "[E]" + [statusIcon] description + " (from: " + startTime + " to: " + endTime + " ) "}.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + " )";
    }
}
