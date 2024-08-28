public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getSaveVersion() {
        return "E/" + this.getStatusIcon() + "/" + description + "/" + start + "/" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: "
                + end +")";
    }
}
