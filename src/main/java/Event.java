public class Event extends Task {
    // todo: use java LocalDateTime
    private String startTime;
    private String endTime;

    public Event(String taskName, boolean taskDone) {
        super(taskName, taskDone);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Event(String taskName, boolean taskDone, String startTime, String endTime) {
        super(taskName, taskDone);
        this.endTime = endTime;
        this.startTime = startTime;
    }

    @Override
    public String getTaskTypeSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s, Start: %s, End: %s", super.toString(), this.startTime, this.endTime);
    }
}
