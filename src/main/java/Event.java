import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = from;
    }

    @Override
    public String taskData() {
        return String.format("E" + super.taskData() + deli + from + deli + to + "\n");
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("yyyy MMM dd  HH:mm")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("yyyy MMM dd  HH:mm")) + ")");
    }
}
