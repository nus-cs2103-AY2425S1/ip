import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public Event(String description, String start, String end) {
        super(description);
        this.start = parseDateTime(start);
        this.end = parseDateTime(end);
    }
    private LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, inputFormatter);
    }


    @Override
    public String toString() {
        return "[E][" + (this.isDone ? "X" : " ") + "] " + this.description +
                " (from: " + this.start.format(outputFormatter) + " to: " + this.end.format(outputFormatter) + ")";
    }
    @Override
    public String parseToFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " +
                this.start.format(inputFormatter) + " | " + this.end.format(inputFormatter);
    }
}