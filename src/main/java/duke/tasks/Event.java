package duke.tasks;

import duke.exceptions.InvalidEventException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event extends Task {

    protected LocalDateTime parsedFromDateTime;
    protected LocalDateTime parsedToDateTime;

    public Event(String description, String from, String to) throws InvalidEventException {
        super(description);

        parsedFromDateTime = null;
        parsedToDateTime = null;

        // Define multiple input format patterns
        final List<DateTimeFormatter> inputFormatters = new ArrayList<>();
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")); // e.g., "2/12/2019 1800"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Dec 2 2019, 6:00 PM"
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy")); // e.g., "2/12/2019"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Jul 2 2019, 5:00 pm"

        // Parse the 'from' field
        parsedFromDateTime = parseDateTime(from, inputFormatters);
        if (parsedFromDateTime == null) {
            throw new InvalidEventException("Invalid 'from' date and time format.");
        }

        // Parse the 'to' field
        parsedToDateTime = parseDateTime(to, inputFormatters);
        if (parsedToDateTime == null) {
            throw new InvalidEventException("Invalid 'to' date and time format.");
        }

        // Ensure that 'from' is before 'to'
        if (parsedFromDateTime.isAfter(parsedToDateTime)) {
            throw new InvalidEventException("'From' date and time must be before 'to' date and time.");
        }
    }

    // Helper method to parse a date string with multiple formats
    private LocalDateTime parseDateTime(String dateTimeStr, List<DateTimeFormatter> formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                if (Objects.equals(formatter, DateTimeFormatter.ofPattern("d/M/yyyy"))) {
                    LocalDate parsedDate = LocalDate.parse(dateTimeStr, formatter);
                    return parsedDate.atStartOfDay(); // Default to midnight
                } else {
                    return LocalDateTime.parse(dateTimeStr, formatter);
                }
            } catch (DateTimeParseException e) {
                // Try the next formatter
            }
        }
        return null; // If no format matched, return null
    }

    // Getter method to format 'from' for display
    public String getFrom() {
        return parsedFromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    // Getter method to format 'to' for display
    public String getTo() {
        return parsedToDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + " [" + this.getStatusIcon() + "] " + super.toString() + " (from: " + this.getFrom() + " to: " + this.getTo() + ")";
    }
}
