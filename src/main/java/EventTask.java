public class EventTask extends Task {
    private String taskName;
    private String startTime;
    private String endTime;

    public EventTask(String taskName, String startTime, String endTime) {
        super(taskName);
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EventTask(String taskName, boolean isCompleted, String startTime, String endTime) {
        super(taskName, isCompleted);
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (super.isCompleted() ? "1" : "0") + " | "
                + taskName + " | " + startTime + "-" + endTime;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + this.startTime + " to: " + this.endTime;
    }
}
