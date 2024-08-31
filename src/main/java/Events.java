import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private String from;
    private String to;
    private LocalDateTime fromStr;
    private LocalTime toStr;

    public Events(String description, String from, String to) throws OptimusException {
        super(description);
        this.from = from;
        this.to = to;
        this.fromStr = parseString(from);
        this.toStr = parseTime(to);
    }

    private LocalDateTime parseString(String dateTime) throws OptimusException {
        DateTimeFormatter[] formats = {
                DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm"),
                DateTimeFormatter.ofPattern("yyyy/MM/d HH:mm"),
        };

        for (DateTimeFormatter diffFormat : formats) {
            try {
                return LocalDateTime.parse(dateTime, diffFormat);
            }
            catch (DateTimeParseException e) {
            }
        }
        throw new OptimusException("Invalid date-time format. Please use one of the following formats: " +
                "d/MM/yyyy HH:mm to HH:mm, yyyy-MM-dd HH:mm to HH:mm, " +
                "d-MM-yyyy HH:mm to HH:mm, yyyy/MM/d HH:mm to HH:mm.");
    }

    private LocalTime parseTime(String toTime) throws OptimusException {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime toLocalTime = LocalTime.parse(toTime, timeFormat);
            return LocalTime.parse(toTime, timeFormat);
        }
        catch (DateTimeParseException e) {
            throw new OptimusException("Invalid 'to' time format. Please use HH:mm format.");
        }

    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + fromStr.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                + " - " + toStr.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }

    @Override
    public String toSaveString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
