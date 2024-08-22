public class Event extends Task {

    private String startTime, endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s, to: %s)", startTime, endTime);
    }
}
