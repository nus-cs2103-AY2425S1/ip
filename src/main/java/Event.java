public class Event extends Task{
    private final String start;
    private final String end;
    public Event(String des, String start, String end) {
        super(des);
        this.end = end;
        this.start = start;
    }

    @Override
    public String getDes() {
        return "[E]" + super.getDes() + " (from: " + start + " to: " + end + ")";
    }
}
