import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    public Event(String description, String fromTime, String toTime, boolean isDone) throws DarkpoolException {
        super(description.trim(), isDone);
        try {
            this.fromTime = LocalDateTime.parse(fromTime, formatter);
            this.toTime = LocalDateTime.parse(toTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from:" + this.fromTime.format(formatter) + " to:" + this.toTime.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return ("E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.fromTime.format(formatter) + " | " + this.toTime.format(formatter) + "\n");
    }
}
