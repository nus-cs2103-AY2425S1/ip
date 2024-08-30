import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private ArrayList<DateTimeFormatter> formatters = new ArrayList<>(
            List.of(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                    DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
    );
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    public LocalDateTime parseDateTime(String dateTime) throws DateTimeParseException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Unable to parse date & time", dateTime, 0);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(outputFormatter)
                + " to: " + this.to.format(outputFormatter) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", super.status() ? 1 : 0, getDescription(),
                from.format(formatters.get(0)), to.format(formatters.get(0)));
    }
}