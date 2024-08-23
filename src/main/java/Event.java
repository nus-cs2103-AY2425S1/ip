public class Event extends Task {
    private String from;
    private String to;

    public Event (String description, String from, String to) throws EmptyDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event (String str) throws IncompleteEventOrDeadlineException, EmptyDescriptionException {
        if (!str.contains("/from ") || !str.contains("/to ")) {
            throw new IncompleteEventOrDeadlineException();
        } else {
            String desc = str.substring(0, str.toLowerCase().indexOf("/from "));
            if (desc.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            description = desc;
            from = str.substring(str.toLowerCase().indexOf("/from ") + 6, str.toLowerCase().indexOf("/to "));
            to = str.substring(str.toLowerCase().indexOf("/to ") + 4, str.length());
            if (from.isEmpty() || to.isEmpty()) {
                throw new IncompleteEventOrDeadlineException();
            }
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
