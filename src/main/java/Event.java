public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + from + to + ")";
    }


}
