package task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + from.format(INPUT_FORMAT) + " - " + to.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        int len = super.toString().length();
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + "\n"
                + UI.INDENT.repeat(3) + " ".repeat(len - 1)
                + "to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
