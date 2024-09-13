package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a deadline task with a description and a deadline date/time.
 */
public class Deadline extends Task {
    private static final List<DateTimeFormatter> DATE_FORMATS_WITH_TIME = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
            DateTimeFormatter.ofPattern("MMM d yyyy HHmm")
    );

    private static final List<DateTimeFormatter> DATE_FORMATS_WITHOUT_TIME = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("MMM d yyyy")
    );

    private static final DateTimeFormatter OUTPUT_FORMATTER_WITH_TIME =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private static final DateTimeFormatter OUTPUT_FORMATTER_WITHOUT_TIME =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDateTime deadlineDateTime;
    private LocalDate deadlineDate;
    private boolean hasTime;

    /**
     * Constructs a new Deadline task.
     *
     * @param description Description of the deadline task.
     * @param deadline    Deadline date/time in string format.
     */
    public Deadline(String description, String deadline) {
        super(description);
        parseDeadline(deadline.trim());
    }

    /**
     * Parses the deadline string and sets the appropriate date or date/time fields.
     *
     * @param deadline Deadline string to parse.
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

        // Try parsing without time
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
        return "[D]" + (isDone ? "[X]" : "[ ]") + " " + description
                + " (by: " + (hasTime ? deadlineDateTime.format(OUTPUT_FORMATTER_WITH_TIME)
                : deadlineDate.format(OUTPUT_FORMATTER_WITHOUT_TIME)) + ")";
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

    /**
     * Checks if the task is due within the next specified number of days.
     *
     * @param days The number of days to check for upcoming deadlines.
     * @return True if the task is due within the specified days, false otherwise.
     */
    @Override
    public boolean isDueWithinDays(int days) {
        LocalDateTime now = LocalDateTime.now();

        if (hasTime) {
            long difference = now.until(deadlineDateTime, ChronoUnit.DAYS);
            return difference >= 0 && difference <= days;
        } else {
            LocalDate today = now.toLocalDate();
            long difference = today.until(deadlineDate, ChronoUnit.DAYS);
            return difference >= 0 && difference <= days;
        }
    }
}
