package duke;

/**
 * The class for all user tasks that have a start and end date.
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * The constructor for the Event class.
     * @param name The description of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String name, String startDate, String endDate) {
        super(name);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    protected String getWriteFormat() {
        return "E , " + (isDone ? "1" : "0") + " , " + name + " , " + startDate + "-" + endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
