public class Event extends Task {
    private String from;
    private String to;

    public Event(boolean done, String task, String from, String to) {
        super(done, task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String task = super.getTask() + " " + String.format("(from: %s to: %s)", from, to);
        return super.getDone() ? "[E][X] " + task : "[E][ ] " + task;
    }

}
