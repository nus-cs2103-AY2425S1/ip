import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getTaskName() + " | " + from.toString() + " | " + to.toString();
    }
}