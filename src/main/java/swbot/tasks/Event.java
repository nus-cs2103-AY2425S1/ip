package swbot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import swbot.BuzzException;

/**
 * A type of task that lets the user create an event to plan for. An event task
 * has a start date and an end date along with a description of what the event is.
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Creates an event task with description, startDate and an endDate
     *
     * @param description describes the task to be done
     * @param startDate starting date of the event
     * @param endDate when the event ends
     * @throws BuzzException if the formatting of the date/time is wrong
     */
    public Event(String description, String startDate, String endDate) throws BuzzException {
        super(description);
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startDate = LocalDateTime.parse(startDate, dateTimeFormatter);
            this.endDate = LocalDateTime.parse(endDate, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BuzzException("WRONG!!! The date and time format should be d/M/yyyy HHmm "
                    + "(e.g., 5/10/2024 0500).");
        }
    }

    /**
     * Returns a string that is the readable format for the startDate
     *
     * @return String that is in a readable format for the user
     */
    public String formatStart() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.startDate.format(dateTimeFormatter);
    }

    /**
     * Returns a string that is the readable format for the endDate
     *
     * @return String that is in a readable format for the user
     */
    public String formatEnd() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");
        return this.endDate.format(dateTimeFormatter);
    }

    /**
     * Returns a string that is the date in a readable format for the output file.
     *
     * @return a full description of the task with readable format for the date
     */
    public String toFileFormat() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | "
                + this.startDate.format(dateTimeFormatter)
                + " | " + this.endDate.format(dateTimeFormatter);
    }

    /**
     * Returns a string describing the entire task with the start and end specified
     *
     * @return a description of the entire event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + formatStart() + " to: " + formatEnd() + ")";
    }
}
