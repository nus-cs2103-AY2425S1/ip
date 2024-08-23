package task;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(int taskID, String description, String from, String to) {
        super(taskID, description);
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom(){
        return from;
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + to + " to: " + from + ")";
    }




}
