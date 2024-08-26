public class Event extends DatedTask {
    public Event(String event, String date) {
        super(event, date);
    }

    public Event() {
        super();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + getDate() + ")";
    }
}
