public class Event extends Task {
    private String fromTime;
    private String toTime;
    public Event(String desc, String fromTime, String toTime) throws EmptyDescException {
        super(desc);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String getOriginalCommand() {
        return "event " + super.getOriginalCommand() + " /from " + this.fromTime + " /to " + this.toTime;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromTime + ", to: " + this.toTime + ")";
    }
}
