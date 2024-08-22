public class Event extends Task {
    String details;
    public Event(String s, String details) {
        super(s);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
