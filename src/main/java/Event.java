public class Event extends DatedTask {
    public Event(String event, String date) {
        super(event, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + getDate() + ")";
    }
}
