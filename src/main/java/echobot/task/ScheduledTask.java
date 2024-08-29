package echobot.task;

import echobot.exception.InvalidDeadlineFormatException;
import echobot.exception.TaskNameEmptyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class ScheduledTask extends Task {
    private final String DATE_TIME_INPUT_FORMAT = "dd-MM-yyyy HH:mm";
    private final String DATE_TIME_OUTPUT_FORMAT = "MMM dd yyyy HH:mm";

    public ScheduledTask(boolean isDone, String taskName) throws TaskNameEmptyException {
        super(isDone, taskName);
    }

    protected LocalDateTime parseInputDateTime(String dateTime) throws InvalidDeadlineFormatException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(this.DATE_TIME_INPUT_FORMAT));
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException(this.DATE_TIME_INPUT_FORMAT);
        }
    }

    protected String formatSaveFileDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(this.DATE_TIME_INPUT_FORMAT));
    }

    protected String formatOutputDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(this.DATE_TIME_OUTPUT_FORMAT));
    }

    /**
     * Checks if the task falls between the date.
     *
     * @param date The date to be checked.
     * @return True if the task falls between the date, otherwise False.
     */
    public abstract boolean isTaskWithinThisDate(LocalDate date);

    @Override
    public String save() {
        return super.save();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
