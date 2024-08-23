package zaibot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public EventTask(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s",
                super.toSaveString(), start, end);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = getFormatter();
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), start.format(formatter), end.format(formatter));
    }
}
