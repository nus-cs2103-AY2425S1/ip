package Task;

public class EventTask extends Task{
    private String event;
    private String from;
    private String to;

    public EventTask(String eventName, String from, String to, boolean completed) {
        super(eventName, completed);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toCacheString() {
        return "E|" + (this.isCompleted() ? "1" : "0") + "|"
                + this.getTask() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        String isCompleted = super.isCompleted() ? "X" : " ";
        return "[E]" + "[" + isCompleted + "] " + super.getTask()
                + " (from:" + this.from + " to:" + this.to + ")";
    }
}
