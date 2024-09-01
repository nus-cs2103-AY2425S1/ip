public class Event extends Task {

    private String start;
    private String end;

    public Event(boolean status, String description, String start, String end) {
        super(status, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "D | " + this.getStatus() + " | " + this.getDescription() + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (from: " + this.start + " end: " + this.end + ")";
    }


}
