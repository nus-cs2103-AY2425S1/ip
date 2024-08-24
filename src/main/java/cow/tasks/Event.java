package cow.tasks;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Alternate constructor for loading isDone directly
     * @param isDone 1 or 0 indicating if task is done
     * @param description The Description of the event
     * @param from from to state the start of the event
     * @param to to state the end of the event
     */
    public Event(String isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * A string that matches the format for writing it to file
     * @return A string to be written to a txt file
     */
    @Override
    public String getSaveData() {
        return "E | " + super.getSaveData() + " | " + this.from + " | " + this.to;
    }
}
