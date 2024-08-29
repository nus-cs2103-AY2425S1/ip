package knight2103.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected final LocalDateTime startTime;
    protected final LocalDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime);
        this.endTime = LocalDateTime.parse(endTime);
    }

    @Override
    public String saveToFileFormat() {
        return "E " + super.saveToFileFormat() + String.format(" | %s | %s", this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.startTime.format(DateTimeFormatter.ofPattern("d MMM (E) HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("d MMM (E) HH:mm")));
    }
}
