public class Event extends Task{
    protected String from;
    protected String to;

    /**
     * Private constructor for a Task
     *
     * @param description A string description of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Alternative constructor for an event, specifying the current status of the task.
     * @param description A string description of the event
     * @param status The current status of the event
     * @param from The starting time of this event
     * @param to The ending time of this event
     */
    protected Event(String description, String status, String from, String to) {
        super(description, Status.valueOf(status));
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[E]");
        str.append(super.toString());
        str.append(String.format("(From:%sTo:%s)", from, to));
        return str.toString();
    }

    /**
     * Returns a csv representation of this event.
     * @return A string in the form "Event,(task description),(task status),(from),(to)"
     */
    @Override
    protected String toCsv() {
        StringBuilder csv = new StringBuilder();
        csv.append("Event,");
        csv.append(super.toCsv());
        csv.append(",");
        csv.append(this.from);
        csv.append(",");
        csv.append(this.to);
        return csv.toString();
    }
}
