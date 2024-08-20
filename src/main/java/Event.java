public class Event extends Task {
    private String from;
    private String to;

    public Event(String task, String deadline, String to) {
        // Call Task constructor
        super(task);

        this.from = deadline;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (%s - %s)", super.toString(), this.from, this.to);
    }
}
