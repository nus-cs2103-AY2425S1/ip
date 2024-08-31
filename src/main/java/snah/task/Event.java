package snah.task;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s (Event: from %s to %s) %s", printStatus(), from, to, getDescription());
    }

    /**
     * Returns true if the task contains the keyword, including the 'from' and 'to'
     * fields.
     * 
     * @param keyword
     * @return boolean
     */
    @Override
    public boolean contains(String keyword) {
        return super.contains(keyword) || from.contains(keyword) || to.contains(keyword);
    }

    public String toSaveFile() {
        return String.format("E:%s:%s:%s:%s", isDone() ? "x" : "", getDescription(), from, to);
    }
}
