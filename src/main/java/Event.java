public class Event extends Task {
    private String start, end, date;
    public Event(String description, String start, String end, int type) {
        super(description, type);
        this.start = start;
        this.end = end;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +  "(" + start + " to " + end + ")";
    }
}
