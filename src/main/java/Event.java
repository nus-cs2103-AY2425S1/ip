public class Event extends Task {
    private final static String type = "[E]";
    private String startTime;
    private String endTime;

    public Event(String desc, String startTime, String endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return type + super.toString() + "(from: " + startTime + " to: " + endTime + ")";
    }
}
