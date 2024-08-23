public class Event extends Task {
    public String start;
    public String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean status, String start, String end) {
        super(description, status);
        this.start = start;
        this.end = end;
    }

    public Event markAsDone() {
        return new Event(this.description, true, this.start, this.end);
    }

    public Event markAsUndone() {
        return new Event(this.description, false, this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (%s %s)", super.toString(), this.start, this.end);
    }

}
