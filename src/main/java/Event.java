public class Event extends Task{

    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super("E", description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String message = "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (from: " + this.startTime + " to: " + this.endTime + ")";
        return message;
    }
}
