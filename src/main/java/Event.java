public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public  String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "E" + " | " + status + " | " + this.description
                + " | " + this.from + " | " + this.to;
    }

    @Override
    public String getStatus() {
        return "[E]" + "[" + super.getStatusIcon() + "]" + " " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
