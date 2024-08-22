public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) throws EmptyDescriptionException, EmptyTimeException {
        super(description);
        this.start = start;
        this.end = end;
        if (description == null) {
            throw new EmptyDescriptionException("Description of event cannot be empty.");
        }
        if (start == null || end == null) {
            throw new EmptyTimeException("Start and End time of event cannot be empty.");
        }
    }

    public String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
