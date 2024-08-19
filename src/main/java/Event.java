public class Event extends Task{
    String from;
    String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String str = String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
        return str;
    }
}
