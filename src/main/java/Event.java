public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    public String stringifyTask() {
        return String.format("E | %d | %s | %s %s", super.getStatus() ? 1 : 0, super.getDesc(), this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
