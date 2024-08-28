package yapyap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event (String description, String from, String to) throws YapperBotException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));;
        } catch (DateTimeParseException e){
            throw new YapperBotException("Invalid date-time format! Please use the format: d/M/yyyy HHmm");
        }

    }

    public Event (String description, String from, String to, boolean isDone) throws YapperBotException {
        super(description);
        this.isDone = isDone;
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));;
        } catch (DateTimeParseException e){
            throw new YapperBotException("Invalid date-time format! Please use the format: d/M/yyyy HHmm");
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | " + this.from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                + " - " + this.to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
