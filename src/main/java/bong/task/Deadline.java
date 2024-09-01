package bong.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends bong.task.Task {
    private final LocalDateTime by;

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),       // e.g., "31/12/2023 2359"
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),       // e.g., "31-12-2023 2359"
            DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"),    // e.g., "31 Dec 2023 2359"
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),    // e.g., "2023-12-31 23:59"
            DateTimeFormatter.ofPattern("d MMM yyyy"),          // e.g., "31 Dec 2023"
            DateTimeFormatter.ofPattern("d/M/yyyy"),            // e.g., "31/12/2023"
            DateTimeFormatter.ofPattern("d-M-yyyy"),            // e.g., "31-12-2023"
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),          // e.g., "2023-12-31"
            DateTimeFormatter.ofPattern("MMMM d, yyyy"),        // e.g., "December 31, 2023"
            DateTimeFormatter.ofPattern("MMM d, yyyy")          // e.g., "Dec 31, 2023"
    };

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String by) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {
                // If it fails, continue to try the next format
            }

            try {
                LocalDate date = LocalDate.parse(by, formatter);
                return date.atStartOfDay();
            } catch (DateTimeParseException e) {
                // If it fails, continue to try the next format
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + by);
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: "
                + by.format(DEFAULT_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by.format(DEFAULT_FORMATTER);
    }
}