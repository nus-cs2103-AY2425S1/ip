package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Converts a LocalDateTime object to a string.
     *
     * @param dateTime
     * @return String
     */
    private static String dateTimeToString(LocalDateTime dateTime) {
        // Correct format: "MMM dd yyyy" e.g., (Oct 15 2019)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    /**
     * Constructor for Deadline.
     *
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the task.
     *
     * @return LocalDateTime
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeToString(by) + ")";
    }
}
