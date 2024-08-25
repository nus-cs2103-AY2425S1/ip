public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime, String taskType) {
        super(description, taskType);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[📅 Event] " + super.toString() + " - Mark your calendar! 🗓️ " +
                "(from: " + startTime + " to: " + endTime + ")";
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }
}
