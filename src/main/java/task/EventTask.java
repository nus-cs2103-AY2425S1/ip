package task;

public class EventTask extends Task {
    private final String fromDate;
    private final String toDate;
    public EventTask(String description, String fromDate, String toDate) {
        super(description, TaskType.EVENT);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return super.toString() + "from: " + this.fromDate + " to: " + this.toDate;
    }
}
