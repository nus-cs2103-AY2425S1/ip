public class EventTask extends Task {
    String from;
    String to;

    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + getDetails();
    }

    @Override
    public String getDetails() {
        return " (from: " + from + " to: " + to + ")";
    }
}
