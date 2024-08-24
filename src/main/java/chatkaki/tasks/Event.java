package chatkaki.tasks;

import chatkaki.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }


    @Override
    public String fileFormat() {
        return "EVENT," + super.fileFormat() + "," + this.start.format(FORMATTER) + "," + this.end.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(FORMATTER) + " to: " + end.format(FORMATTER) + ")" ;
    }
}

