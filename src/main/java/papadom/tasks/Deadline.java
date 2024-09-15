package papadom.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 * A deadline task contains a description and a deadline string.
 */
public class Deadline extends Task {
    public static final String DEADLINE_ICON = "[D]";
    protected String deadline; // Store the deadline as a formatted String

    /**
     * Constructs a Deadline task with a description and a deadline string.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline string (should be in "yyyy-MM-dd" or
     *                    "yyyy-MM-dd HH:mm" format).
     */
    public Deadline(String description, String by) {
        super(description);
        assert !description.isEmpty() : "Description cannot be empty";

        DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");
        DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        // Handle the input format and format accordingly
        if (by.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
            // First condition: It's in "yyyy-MM-dd HH:mm" format
            LocalDateTime dateTime = LocalDateTime.parse(by, inputDateTimeFormatter);
            this.deadline = dateTime.format(outputDateTimeFormatter); // Store formatted date-time string
        } else if (by.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // Second condition: It's in "yyyy-MM-dd" format
            LocalDate date = LocalDate.parse(by, inputDateFormatter);
            this.deadline = date.format(outputDateFormatter); // Store formatted date-only string
        } else {
            // Third condition: It's already in the preformatted "MMM d yyyy, hh:mm a" format
            this.deadline = by; // Assume it's already correctly formatted and store it as-is
        }
    }

    /**
     * Returns a string representation of the deadline task, including the
     * deadline date as "MMM d yyyy" or "MMM d yyyy, hh:mm a".
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + " (by: " + deadline + ")";
    }
}
