package sammy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDate(deadline);
    }

    private LocalDateTime parseDate(String date) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),
                DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMMM d, yyyy HHmm"),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),
                DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
                DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"),
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // continue to the next format
            }
        }

        throw new DateTimeParseException("Unrecognized date format: " + date, date, 0);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}

