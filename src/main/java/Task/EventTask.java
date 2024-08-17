package Task;

public class EventTask extends Task{
    private String event;
    private String from;
    private String to;

    public EventTask(String eventName, String from, String to) {
        super(eventName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[E]" + "[" + isCompleted + "] " + super.getTask()
                + " (from:" + this.from + " to:" + this.to + ")";
    }
}
