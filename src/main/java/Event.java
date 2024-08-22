public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[E] [%s] %s (from: %s to: %s)", mark, super.description, this.from, this.to);
    }
}
