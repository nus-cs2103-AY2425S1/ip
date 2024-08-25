public class Event extends Task{
    private String description;
    private String start;
    private String end;
    private boolean isMarked;

    public Event(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.isMarked = false;
    };

    @Override
    public void mark() {
        this.isMarked = true;
    }

    @Override
    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String toString() {
        return "[E] " + this.description +
                " (from: " + this.start +
                " to: " + this.end + ")";
    }
}
