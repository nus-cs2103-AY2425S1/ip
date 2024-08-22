public class Event extends Task{
    private final String from;
    private final String to;
    public Event(String name, String from, String to) {
        super(name.trim());
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString()
            + " (from: " + from + " to: " + to + ")");
    }
}
