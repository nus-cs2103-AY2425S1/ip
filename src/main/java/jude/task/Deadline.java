package jude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jude.JudeException;

/**
 * Represents a task with a deadline.
 * Stores the date and time as LocalDateTime.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;

    /**
     * Creates the Deadline object.
     * @param description of the Task.
     * @param deadline of which the format is expected to be of the direct user input.
     * @throws JudeException if the format of the deadline is not in the expected format.
     */
    public Deadline(String description, String deadline) throws JudeException {
        super(description);
        dateTime = parseDateAndTime(deadline);
    }

    /**
     * Creates the Deadline object.
     * @param description of the Task.
     * @param dateTime of which the format is expected to be of the save file format.
     * @param isDone represents that status of the task isDone.
     * @throws JudeException if the format of the deadline is not in the expected format.
     */
    public Deadline(String description, String dateTime, boolean isDone) throws JudeException {
        super(description, isDone);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    /**
     * Takes in the String representation of the date and time of a particular format.
     * Parses the String representation to LocalDateTime object.
     * @param deadline the String which parsed from the user input, containing information about the deadline.
     * @throws JudeException if the format of the input is not valid.
     */
    public LocalDateTime parseDateAndTime(String deadline) throws JudeException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(deadline, format);
        } catch (DateTimeParseException de) {
            throw new JudeException("Wrong date or time format was provided."
                    + " Provide: day/month/year time (e.g. 1/1/2000 1800).");
        }
    }

    @Override
    public String toStringDetails() {
        return String.format("[D]%s (by: %s)",
                super.toStringDetails(), this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }

    @Override
    public String toFileFormat() {
        return String.format("%s | %d | %s | %s",
                "D", getStatusBinary(), getDescription(), this.dateTime);
    }

    @Override
    public LocalDateTime getDueDateTime() {
        return dateTime;
    }
}
