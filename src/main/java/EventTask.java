/**
 * These are tasks with specific start and end date time
 */
public class EventTask extends Task {

    private String startDateTime;
    private String endDateTime;
    /**
     * Constructur for todo task
     * @param description description of task
     */
    public EventTask(String description, String startDateTime, String endDateTime) {
        super(description);
        startDateTime = startDateTime.trim();
        endDateTime = endDateTime.trim();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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
