package twilight;

/**
 * Represents an Event with a particular startTime and endTime to be stored in a task list.
 */
public class Event extends Task {
    protected String startTime;
    protected String endTime;

    /**
     * Instantiates an Event task.
     *
     * @param description Event name.
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates an event while labelling at as complete or incomplete.
     * To be used when loading tasks.
     *
     * @param isDone Event completion status.
     * @param description Event Name.
     * @param startTime
     * @param endTime
     */
    public Event(boolean isDone, String description, String startTime, String endTime, String tag) {
        super(description, isDone, tag);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + this.startTime + " to: " + this.endTime + getTagString();
    }

    @Override
    public String toStorageString() {
        return "E," + super.toStorageString() + "," + startTime + "," + endTime + "," + this.tag;
    }
}
