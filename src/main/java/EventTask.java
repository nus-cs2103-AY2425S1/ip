public class EventTask extends Task {
    private final String from;
    private final String to;

    public EventTask(String taskDescription) {
        super(taskDescription);
        this.from = "";
        this.to = "";
    }

    public EventTask(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    private EventTask(boolean isDone, String taskDescription, String from, String to) {
        super(isDone, taskDescription);
        this.from = from;
        this.to = to;
    }

    @Override
    public Task markAsDone() {
        return super.isDone
            ? this 
            : new EventTask(true, super.taskDescription, this.from, this.to);
    }

    @Override 
    public Task markAsUndone() {
        return super.isDone
            ? new EventTask(super.taskDescription, this.from, this.to)
            : this;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
