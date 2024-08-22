public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) throws DonnaException {
        super(description);
        if (from.trim().isEmpty() && to.trim().isEmpty()) {
            throw DonnaException.emptyEventTime();
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return super.toString() //type, status & desc
                + "(from: " + from + " to: " + to + ")";
    }
}
