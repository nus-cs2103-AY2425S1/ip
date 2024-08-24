public class Event extends Task {
    private String from;
    private String to;

    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[E] " + task + " (from: " + from + " to: " + to + ")";
    }
}
