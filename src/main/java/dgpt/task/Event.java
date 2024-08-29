package dgpt.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");

    public Event(String description, String startTime, String endTime) throws DateTimeParseException {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, inputFormatter);
        this.endTime = LocalDateTime.parse(endTime, inputFormatter);
    }

    public String getFromTime() {
        return this.startTime.format(outputFormatter);
    }

    public String getToTime() {
        return this.endTime.format(outputFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromTime() + " to: " +
                this.getToTime() + ")";
    }
}
