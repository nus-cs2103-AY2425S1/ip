package snah.task;

/**
 * Class to handle Event tasks
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructor for Event class
     * @param description Description of the task
     * @param from        Start time of the event
     * @param to          End time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s (Event: from %s to %s) %s", printStatus(), from, to, getDescription());
    }

    /*
     * Returns true if the task contains the keyword,including the 'from' and 'to'
     * fields.
     * @param keyword
     * @return boolean
     */
    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || from.contains(keyword) || to.contains(keyword);
    }

    /**
     * Returns the Event in the format to save to file
     * @return Event in the format to save to file
     */
    @Override
    public String toSaveFile() {
        return String.format("E:%s:%s:%s:%s", isDone() ? "x" : "", getDescription(), from, to);
    }
}
