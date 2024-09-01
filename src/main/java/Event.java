public class Event extends Task {
    public String from;
    public String to;

    public String statement;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String write()
    {
        statement = String.format("E | %d | %s | %s-%s\n", this.isDone ? 1 : 0, this.description, this.from, this.to);
        return statement;
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}