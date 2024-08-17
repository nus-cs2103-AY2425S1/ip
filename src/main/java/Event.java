public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public String getDescription() {
        return this.description + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
