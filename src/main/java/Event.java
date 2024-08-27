public class Event extends Task {
    String date;
    String from;
    String to;
    public Event(String description, String date, String from, String to) {
        super(description);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public Event(String description, String date, String from, String to, boolean isDone) {
        super(description, isDone);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    public Event() {
        super();
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return String.format("[X] | Event | %s | %s | %s to %s", getDescription(), this.date, this.from, this.to);
        } else {
            return String.format("[ ] | Event | %s | %s | %s to %s", getDescription(), this.date, this.from, this.to);
        }
    }
}
