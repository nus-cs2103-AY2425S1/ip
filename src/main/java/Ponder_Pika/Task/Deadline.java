package Ponder_Pika.Task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String saveFullDetails() {
        return String.format("D | %b | %s | %s", isDone(), getDescription(),
                this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}
