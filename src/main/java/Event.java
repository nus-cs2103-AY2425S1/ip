public class Event extends Task {
    private final String from;
    private final String to;

    Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String toFile() { return "E/" + super.toFile() + "/" + from + "/" + to; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
}
