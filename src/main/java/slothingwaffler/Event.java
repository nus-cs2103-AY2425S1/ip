package slothingwaffler;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, Task.dtf1);
        this.to = LocalDateTime.parse(to, Task.dtf1);
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + this.from.format(Task.dtf1) + "-" + this.to.format(Task.dtf1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Task.dtf2) + " to: " + this.to.format(Task.dtf2) + ")";
    }

}
