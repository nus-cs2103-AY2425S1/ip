public class EventTask extends Task {
    protected String from;
    protected String to;

    public EventTask(String description, String from, String to) throws TaskArgumentMissingException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "Did you forget your EVENT?\nBecause you tried to make an event of nothing!";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
