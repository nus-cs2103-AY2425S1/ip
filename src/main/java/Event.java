public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, int done) {
        super(description);
        this.from = from;
        this.to = to;
        if (done == 1) {
            this.isDone = true;
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s | %d | %s | %s | %s", this.getTaskType(), (this.isDone ? 1 : 0), this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
