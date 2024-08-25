public class Event extends Task{
    String from;
    String to;

    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String name, String from, String to) {
        this(name, from, to, false);
    }

    @Override
    public String toString() {
        String str = String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
        return str;
    }

    @Override
    public String encode() {
        return "E|" + super.encode() + "|" + this.from + "|" + this.to;
    }
}
