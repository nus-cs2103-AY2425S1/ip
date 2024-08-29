package task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    public static Event of(String[] args) {
        return new Event(args[1], args[2], args[3]);
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toData() {
        return super.toData() + "|" + start + "|" + end;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), start, end);
    }
}
