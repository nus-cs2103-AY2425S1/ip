public class Event extends Task{

    private final String from;
    private final String to;

    public Event(String _description, String _from, String _to) {
        super(_description);
        this.from = _from;
        this.to = _to;

    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
