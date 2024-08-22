public class Event extends Task {
    private String start;
    private String end;

    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (from: %s to: %s )", super.toString(), start, end);
    }


}
