/**
 * This class defines and prodides functionality for an event task.
 * <p>
 * An event task has a start date and an end date.
 */
public class EventTask extends Task{
    
    /** The start date for the event */
    private String startDate;
    /** The end date for the event */
    private String endDate;

    /**
     * Creates an event task object based on its description, start date and end date.
     * @param description The description of the event
     * @param startDate The starting period for the event
     * @param endDate The ending period for the event
     */
    EventTask(String description, String startDate, String endDate) {

        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + "(From: " + this.startDate + " To: " + this.endDate + ")";
    }
}

