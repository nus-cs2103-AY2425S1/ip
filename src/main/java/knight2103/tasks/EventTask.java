package knight2103.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    protected final LocalDateTime startTime;
    protected final LocalDateTime endTime;

    /**
     * Constructs a task Event object which contains a description of the event task.
     * The object by default has the completion status set as not done.
     * The object also contains information of the start and end time of event task.
     */
    public EventTask(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    @Override
    public String toStringInFile() {
        return "E " + super.toStringInFile() + String.format(" | %s | %s", this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern("d MMM (E) HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("d MMM (E) HH:mm")));
    }
}
