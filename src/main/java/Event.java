public class Event extends Task {
    private String start;
    private String end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), start, end);
    }
}
