public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String task, String from, String to, boolean isCompleted) {
        super(task, isCompleted);
        this.from = from;
        this.to = to;
    }

    public String toFileString() {
        return "E|" + super.toFileString() + "|" + from + "|" + to;
    }

    public String toString() {
        return "[E]" + super.toString() + "(" + from + to + ")";
    }

}
