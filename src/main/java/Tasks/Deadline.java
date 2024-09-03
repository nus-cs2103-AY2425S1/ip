package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a deadline task with a description and a deadline date/time.
 */
public class Deadline extends Task {
    protected static final List<DateTimeFormatter> DATE_FORMATS_WITH_TIME = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm")
    );

    protected static final List<DateTimeFormatter> DATE_FORMATS_WITHOUT_TIME = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("MMM d yyyy")
    );

    protected static final DateTimeFormatter OUTPUT_FORMATTER_WITH_TIME =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    protected static final DateTimeFormatter OUTPUT_FORMATTER_WITHOUT_TIME =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDateTime deadlineDateTime;
    protected LocalDate deadlineDate;
    protected boolean hasTime;
    /**
     * Constructs a new Deadline task.
     *
     * @param description The description of the deadline task.
     * @param deadline    The deadline date/time in string format.
     */
    public Deadline(String description, String deadline) {
        super(description);
        parseDeadline(deadline.trim());
    }

    /**
     * Parses the deadline string and sets the appropriate date or date/time fields.
     *
     * @param deadline The deadline string to parse.
     */
    private void parseDeadline(String deadline) {
        // Try parsing with time first
        for (DateTimeFormatter formatter : DATE_FORMATS_WITH_TIME) {
            try {
                this.deadlineDateTime = LocalDateTime.parse(deadline, formatter);
                this.hasTime = true;
                return;
            } catch (DateTimeParseException e) {
                // Continue trying other formats
            }
        }
        // If parsing with time fails, try parsing without time
        for (DateTimeFormatter formatter : DATE_FORMATS_WITHOUT_TIME) {
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

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string describing the deadline task.
     */
    @Override
    public String toString() {
        if (hasTime) {
            return "[D]" + (isDone ? "[X]" : "[ ]") + " " + description
                    + " (by: " + deadlineDateTime.format(OUTPUT_FORMATTER_WITH_TIME) + ")";
        } else {
            return "[D]" + (isDone ? "[X]" : "[ ]") + " " + description
                    + " (by: " + deadlineDate.format(OUTPUT_FORMATTER_WITHOUT_TIME) + ")";
        }
    }

    /**
     * Returns the file format string of the deadline task for storage.
     *
     * @return A string suitable for storing in a file.
     */
    @Override
    public String toFileFormat() {
        if (hasTime) {
            return "D | " + (isDone ? "1" : "0") + " | " + description
                    + " | " + deadlineDateTime.format(DATE_FORMATS_WITH_TIME.get(0));
        } else {
            return "D | " + (isDone ? "1" : "0") + " | " + description
                    + " | " + deadlineDate.format(DATE_FORMATS_WITHOUT_TIME.get(0));
        }
    }
}
