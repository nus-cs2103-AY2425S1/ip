public class Event extends Task{
    private final String start;
    private final String end;
    public Event(String des, String start, String end) {
        super(des);
        this.end = end;
        this.start = start;
    }

    public Event(String des, boolean isMark, String start, String end) {
        super(des);
        this.end = end;
        this.start = start;
        this.isMark = isMark;
    }

    @Override
    public String getDes() {
        return "[E]" + super.getDes() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toString() {
        return String.format("E %d %s | %s | %s", isMark? 1 : 0, des, start, end);
    }
}
