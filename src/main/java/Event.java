public class Event extends Task{
    protected String startDt;
    protected String endDt;

    /**
     * Constructs an Event object with the specified description, start date-time, and end date-time.
     *
     * @param description The description of the event.
     * @param startDt The start date-time of the event in the specified format.
     * @param endDt The end date-time of the event in the specified format.
     */
    public Event(String description, String startDt, String endDt) {
        super(description, "E");
        this.startDt = startDt;
        this.endDt = endDt;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.startDt + " to " + this.endDt + ")";
    }

    @Override
    public String toFileSaveString() {
        return this.taskType + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.startDt + "|" + this.endDt;
    }

}
