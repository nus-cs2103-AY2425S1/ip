package elara.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public EventTask(String desc, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(desc, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                start, end);
    }
}
