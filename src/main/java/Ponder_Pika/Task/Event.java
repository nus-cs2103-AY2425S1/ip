package Ponder_Pika.Task;

public class Event extends Task{
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveFullDetails() {
        return String.format("E | %b | %s | %s | %s", isDone(), getDescription(), this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", from, to);
    }
}
