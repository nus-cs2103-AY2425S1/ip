public class Event extends Task{
    private final String from;
    private final String to;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String taskData() {
        return String.format("E" + super.taskData() + deli + from + deli + to + "\n");
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString()
            + " (from: " + from + " to: " + to + ")");
    }
}
