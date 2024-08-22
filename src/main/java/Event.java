public class Event extends Task {
    private String start;

    private String end;
    public Event (int taskNumber, String taskName, boolean taskCompletionStatus, String start, String end) {
        super(taskNumber, taskName, taskCompletionStatus);
        this.start = start;
        this.end = end;
    }
}
