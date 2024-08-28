public class Event extends Task {

    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String desc = String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
        return desc;
    }
}
