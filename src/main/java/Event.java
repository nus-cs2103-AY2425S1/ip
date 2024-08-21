public class Event extends Task {
    private String start;
    private String end;

    public Event(String t, String s, String e) {

        super(t);
        this.start = s;
        this.end = e;
        this.type = "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}