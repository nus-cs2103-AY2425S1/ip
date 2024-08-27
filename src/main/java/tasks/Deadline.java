package tasks;

import java.time.LocalDateTime;
import parser.DateTimeHandler;

public class Deadline extends Task {
    private final LocalDateTime time;
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }
    @Override
    public String toString() {
        String dueDate = DateTimeHandler.formatDateTime(this.time);
        if (super.isComplete()) {
            return ("[D] " + super.toString() + " (by: " + dueDate + ")");
        }
        return ("[D] " + super.toString() + " (by: " + dueDate + ")");
    }
}
