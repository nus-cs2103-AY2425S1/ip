public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String name, TaskType taskType, String startTime, String endTime) {
        super(name, taskType);
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
