public class Event extends Task {
    String start;
    String end;
    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from : " + start + " to: " + end + ")";
    }
}
