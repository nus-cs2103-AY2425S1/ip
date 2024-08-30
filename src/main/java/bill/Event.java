package bill;

/**
 * The Event class extends the Task class, and allows creating tasks with a range of time, from a certain start period to a certain end period.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Initializes Event.
     * There is currently no restriction on the range period, all string values are accepted.
     *
     * @param description Description of event.
     * @param from Start time period of task in any format.
     * @param to End time period of task in any format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to +  ")";
    }
}