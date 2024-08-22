public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws JiaException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isEmpty() || this.from.isEmpty() || this.to.isEmpty()) {
            throw new JiaException("What time is your event?? :o");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
