/**
 * These are tasks with specific start and end date time
 */
public class EventTask extends Task {

    private String startDateTime;
    private String endDateTime;
    /**
     * Constructur for Event task
     *
     * @param description description of task
     * @param startDateTime start date of task
     * @param endDateTime end date of task
     */
    public EventTask(String description, String startDateTime, String endDateTime) {
        super(description);
        startDateTime = startDateTime.trim();
        endDateTime = endDateTime.trim();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructur for Event task
     *
     * @param description description of task
     * @param startDateTime start date of task
     * @param endDateTime end date of task
     * @param isDone status of task
     */
    public EventTask(String description, String startDateTime,
                     String endDateTime, boolean isDone) {
        this(description, startDateTime, endDateTime);
        this.isDone = isDone;
    }



    /**
     * Overrides string representation to show more complete information of Event task
     *
     * @return string representation of Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDateTime
                + " to: " + this.endDateTime + ")";
    }

}
