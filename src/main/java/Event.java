public class Event extends Task {
    public Event(String description) {
        super(description);
    }

    @Override
    public String getTaskTypeIcon() {
        return "[E]";
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }
}
