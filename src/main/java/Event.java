import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(boolean status, String description, LocalDateTime start, LocalDateTime end) {
        super(status, description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "E | " + this.getStatus() + " | " + this.getDescription() + " | " + this.start.format(FILE_DATE_TIME_FORMATTER) + " | " + this.end.format(FILE_DATE_TIME_FORMATTER);
    }

    @Override
    public String toUIString() {
        return "The appropriate question is: ‘When the hell are they?’ Your event is now set in time!\n";
    }
    @Override
    public String toString() {
        return "[E][" + (this.getStatus() ? "X" : " ") + "] " +
                this.getDescription() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " end: " + this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }


}
