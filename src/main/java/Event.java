public class Event extends Task{

    String start;
    String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + start + "to:" + end + ")";
    }

    public String changeFormat() {
        return "E | " + (this.getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description + " | " + this.start + " | " + this.end;
    }
}
