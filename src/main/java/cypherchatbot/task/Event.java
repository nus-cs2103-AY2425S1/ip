package cypherchatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String str = this.completed ? "[E][X] " : "[E][ ] ";
        str += String.format("%s (from: %s || to: %s)", this.description,
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                    this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        return str;
    }

    @Override
    public String toStringInFile() {
        int val = this.completed ? 1 : 0;
        return String.format("E|%d|%s|%s|%s", val, this.description,
                this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                    this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
