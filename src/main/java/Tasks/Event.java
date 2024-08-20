public class Event extends Task {
    private final String startTime;
    private final String endTime;
    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), startTime, endTime);
    }
}
