public class Event extends Task {
    private final String startDateTime;
    private final String endDateTime;

    /**
     * Constructor for Deadline class.
     *
     * @param description   Description of the task.
     * @param startDateTime Start date and time of the event.
     * @param endDateTime   End date and time of the event.
     */
    public Event(String description, String startDateTime, String endDateTime) {
        super(TaskType.EVENT, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation of the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }
}
