public class EventTask extends Task {
    String from;
    String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String taskType() {
        return "Event Task";
    }

    @Override
    public String toString() {
        String formattedDate = String.format("(from: %s to: %s)", from, to);
        return "[E]" + super.toString() + " " + formattedDate;
    }
}
