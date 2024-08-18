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
        if (super.isComplete()) {
            return ("[E] " + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")");
        }
        return ("[E] " + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")");
    }
}

