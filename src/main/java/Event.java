public class Event extends Task {
    protected String endTime;
    protected String startTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDeadline() {
        return this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    @Override
    public String toString() {
        return "{E}" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
