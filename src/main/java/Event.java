public class Event extends Task {

    private String startTime, endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String name, String startTime, String endTime, boolean done) {
        super(name, done);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s, to: %s)", startTime, endTime);
    }

    @Override
    public String toFileString() {
        return String.format("D\n%s%s\n%s\n", super.toFileString(), startTime, endTime);
    }
}
