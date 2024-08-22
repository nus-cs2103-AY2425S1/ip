public class Event extends Task {
    private String date;
    public Event(String event, String date) {
        super(event);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + date + ")";
    }
}
