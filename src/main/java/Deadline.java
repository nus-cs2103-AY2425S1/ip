import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
public class Deadline extends Task {
    private LocalDateTime by;
    private ArrayList<DateTimeFormatter> formatters = new ArrayList<>(
            List.of(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                    DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"))
    );
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    public LocalDateTime parseDateTime(String by) throws DateTimeParseException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DateTimeParseException("Unable to parse date & time", by, 0);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", super.status() ? 1 : 0, getDescription(),
                by.format(formatters.get(0)));
    }
}
