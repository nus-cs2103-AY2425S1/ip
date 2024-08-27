public class Event extends Task{
    private String start;
    private String end;

    public Event(String title, String start, String end) {
        super(title);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toStorageString() {
        if (this.isDone()) {
            return "E | 1 | " + this.getTitle() + " | " + this.start + " | " + this.end;
        } else {
            return "E | 0 | " + this.getTitle() + " | " + this.start + " | " + this.end;
        }
    }
}
