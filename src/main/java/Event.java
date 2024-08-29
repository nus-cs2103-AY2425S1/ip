public class Event extends Task {
    protected String start;
    protected String end;
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (this.getIsDone()? "1" : "0")
                + " | " + super.toFileString() + " | "
                + this.start + " | " + this.end;
    }
}
