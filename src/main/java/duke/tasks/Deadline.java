package duke.tasks;
import duke.exceptions.InvalidDeadlineException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Deadline extends Task {

    protected LocalDateTime parsedDateTime;

    public Deadline(String description, String by) throws InvalidDeadlineException {
        super(description);

        parsedDateTime = null;
        final List<DateTimeFormatter> inputFormatters = new ArrayList<>();
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")); // e.g., "2/12/2019 1800"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Dec 2 2019, 6:00 PM"
        inputFormatters.add(DateTimeFormatter.ofPattern("d/M/yyyy")); // e.g., "2/12/2019"
        inputFormatters.add(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")); // e.g., "Jul 2 2019, 5:00 pm"

        // Try to parse the input using different formatters
        for (DateTimeFormatter formatter : inputFormatters) {
            try {
                if (Objects.equals(formatter, DateTimeFormatter.ofPattern("d/M/yyyy"))) {
                    LocalDate parsedDate = LocalDate.parse(by, formatter);
                    parsedDateTime = parsedDate.atStartOfDay(); // Default to midnight
                    break;
                } else {
                    parsedDateTime = LocalDateTime.parse(by, formatter);
                }
            } catch (DateTimeParseException e) {
                // Continue trying with the next formatter
            }
        }

        if (parsedDateTime == null) {
            throw new InvalidDeadlineException("Your deadline is invalid. ");
        }
    }

    public String getBy() {
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + " [" + this.getStatusIcon() + "] " + super.toString() + " (by: " + this.getBy() + ")";
    }
}
