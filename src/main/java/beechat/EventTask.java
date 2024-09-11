package beechat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(formatter) + " | " + to.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + "(from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }
}
