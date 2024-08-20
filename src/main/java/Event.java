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

    public Event(String description, boolean isDone,
                 String date, String from, String to) {
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
            return String.format("[E][X]: %s (%s, %s to %s)", getDescription(), this.date, this.from, this.to);
        } else {
            return String.format("[E][ ]: %s (%s, %s to %s)", getDescription(), this.date, this.from, this.to);
        }
    }
}
