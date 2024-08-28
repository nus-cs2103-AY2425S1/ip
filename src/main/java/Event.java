public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event class.
     * @param description the description of the task/event
     * @param from when the event begins
     * @param to when the event ends
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStringSave() {
        int indicator = this.isDone ? 1 : 0;
        return "E | " + indicator + " | " + this.description
                + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns a string representation of the task.
     * @return task description with status and event period
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.from + " " + this.to + ")";
    }
}
