public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String desc, String startTime, String endTime, boolean isDone) {
        super(desc, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getSaveTaskString() {
        return "E" + super.getSaveTaskString() + "|" + this.startTime + "|" + this.endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + this.startTime + "|to: " + this.endTime + ")";
    }
}
