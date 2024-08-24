import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    protected LocalDateTime deadlineDateTime;
    protected LocalDate deadlineDate;
    protected boolean hasTime;

    protected static final List<DateTimeFormatter> dateFormatsWithTime = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm")
    );
    protected static final List<DateTimeFormatter> dateFormatsWithoutTime = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("MMM d yyyy")
    );

    protected static final DateTimeFormatter outputFormatterWithTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    protected static final DateTimeFormatter outputFormatterWithoutTime = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String deadline) {
        super(description);
        parseDeadline(deadline.trim());
    }

    private void parseDeadline(String deadline) {
        // Try parsing with time first
        for (DateTimeFormatter formatter : dateFormatsWithTime) {
            try {
                this.deadlineDateTime = LocalDateTime.parse(deadline, formatter);
                this.hasTime = true;
                return;
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }
        // If parsing with time fails, try parsing without time
        for (DateTimeFormatter formatter : dateFormatsWithoutTime) {
            try {
                this.deadlineDate = LocalDate.parse(deadline, formatter);
                this.hasTime = false;
                return;
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }
        // If none of the formats match, throw an exception
        throw new DateTimeParseException("Invalid date/time format", deadline, 0);
    }

    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + (isDone ? "[X]" : "[ ]") + " " + description + " (by: " + deadlineDateTime.format(outputFormatterWithTime) + ")";
        } else {
            return "[D]" + (isDone ? "[X]" : "[ ]") + " " + description + " (by: " + deadlineDate.format(outputFormatterWithoutTime) + ")";
        }
    }

    @Override
    public String toFileFormat() {
        if (hasTime) {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadlineDateTime.format(dateFormatsWithTime.get(0));
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadlineDate.format(dateFormatsWithoutTime.get(0));
        }
    }
}
