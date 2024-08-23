public class Event extends Task {
    private String from;
    private String to;

    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event (String str) {
        this(str.substring(0, str.toLowerCase().indexOf("/from ")),
                str.substring(str.toLowerCase().indexOf("/from ") + 6, str.toLowerCase().indexOf("/to ")),
                str.substring(str.toLowerCase().indexOf("/to ") + 4, str.length()));
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
