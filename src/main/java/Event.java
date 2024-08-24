public class Event extends Task {

    private String start;
    private String end;

    public Event(boolean isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String fileFormat() {
        return "EVENT," + super.fileFormat() + "," + this.start + "," + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")" ;
    }
}

