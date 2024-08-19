public class Event extends Task{
    protected String startWhen;
    protected String endWhen;

    public Event(String description, String startWhen, String endWhen) {
        super(description);
        this.startWhen = startWhen;
        this.endWhen = endWhen;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (from: " + this.startWhen + " to: " + this.endWhen + ")";
    }
}
