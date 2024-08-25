public class Event extends Task {
    // todo: use java LocalDateTime
    private String startTime;
    private String endTime;

    public Event(String taskName, boolean taskDone) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.EVENT);
        this.startTime = "None";
        this.endTime = "None";
    }
    public Event(String taskName, boolean taskDone, String startTime, String endTime) {
        super(taskName, taskDone);
        super.setTaskType(TaskTypes.DEADLINE);
        this.startTime = startTime;
        this.endTime = endTime;
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

    @Override
    public String getTaskTypeSymbol() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s", super.getTaskName(), this.startTime, this.endTime);
    }
}
