public class EventTask extends Task {
    private String startTime;
    private String endTime;

    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private String getTypeLabel() {
        return "E";
    }

    private String getTimingLabel() {
        return String.format("(from: %s to: %s)", this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s %s", this.getTypeLabel(), super.toString(), this.getTimingLabel());
    }
}
