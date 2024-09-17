package swbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import swbot.BuzzException;

/**
 * A type of task that lets the user create a deadline. A deadline task has
 * a date to finish by. It also has a description.
 */
public class Deadline extends Task {
    protected LocalDateTime date;

    /**
     * Creates a deadline task with a description and takes in a date/time as a string and
     * parses through it, turning it into a LocalDateTime object to be stored.
     *
     * @param description name of the task
     * @param date date to be completed by
     * @throws BuzzException if formatting of the date string is wrong
     */
    public Deadline(String description, String date) throws BuzzException {
        super(description);
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.date = LocalDateTime.parse(date, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BuzzException("WRONG!!! The date and time format should be d/M/yyyy HHmm "
                    + "(e.g., 5/10/2024 0500).");
        }
    }

    /**
     * Returns a string in the readable format for the file that stores the tasklist
     *
     * @return readable format for the date and time
     */
    public String toFileFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + this.date.format(dateTimeFormatter);
    }

    /**
     * Returns a string of the parsed date and time of the deadline event
     *
     * @return string of the formatted date and time
     */
    public String formatBy() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.date.format(dateTimeFormatter);
    }

    /**
     * Returns a string describing the entire task with deadline date and time parsed
     *
     * @return description and time for the task to be completed by
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy() + ")";
    }
}
