package bing.task;

import bing.task.Task;
import bing.task.TaskStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    public Event(String info, LocalDateTime from, LocalDateTime to) {
        super(info);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return "E | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo() + " | " + from.format(formatter) + " | " + to.format(formatter);
    }
}
