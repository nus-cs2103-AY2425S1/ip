package Ponder_Pika.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveFullDetails() {
        return String.format("E | %b | %s | %s | %s", isDone(), getDescription(), this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")), this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
