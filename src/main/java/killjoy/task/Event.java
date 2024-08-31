package killjoy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskInfo() {
        return "EVENT|" + String.valueOf(isDone ? 1 : 0) + "|" + this.description + "|" +
                String.valueOf(this.from).replace("T", " ") + "|"
                + String.valueOf(this.to).replace("T", " ") + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (from: " + this.from.format(formatter) + " to: "
                + this.to.format(formatter) + ")";
    }
}
