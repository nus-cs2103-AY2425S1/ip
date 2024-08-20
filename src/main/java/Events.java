public class Events extends Task {
    protected String startTime;
    protected String endTime;
    public Events(String description, String endTime, String startTime) {
        super(description);
        this.endTime = endTime;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
