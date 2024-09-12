package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exceptions.BrockException;

/**
 * Class representing a deadline task.
 */
public class Deadline extends Task {
    private final LocalDate dueDate;
    private final LocalTime dueTime;

    /**
     * Sets the deadline task description and due date.
     * A dummy value is given for due time.
     * Sets the deadline task status to be uncompleted.
     *
     * @param description Task description.
     * @param dateTimeStrings {@code String[]} of date time strings
     * @throws BrockException If due date is invalid.
     */
    public Deadline(String description, String ...dateTimeStrings) throws BrockException {
        super(description);
        try {
            this.dueTime = dateTimeStrings.length == 1
                    ? LocalTime.MAX
                    : this.parseTime(dateTimeStrings[1]);
            this.dueDate = LocalDate.parse(dateTimeStrings[0]);
            this.validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
        }
    }

    /**
     * Converts due time from {@code String} to {@code LocalTime}.
     *
     * @param dueTimeString Due time as a {@code String}.
     * @return Due time as a {@code LocalTime}.
     */
    private LocalTime parseTime(String dueTimeString) {
        String hours = dueTimeString
                .substring(0, 2);
        String minutes = dueTimeString
                .substring(2);
        return LocalTime.of(Integer.parseInt(hours),
                Integer.parseInt(minutes));
    }

    /**
     * Checks if the due date is valid.
     * If due time is provided (ie: not a dummy), checks if it is valid.
     *
     * @throws BrockException If they are not valid.
     */
    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (this.dueDate.isBefore(today)) {
            throw new BrockException("Due date cannot be earlier than today!");
        }
        if (this.dueTime != LocalTime.MAX) {
            LocalTime now = LocalTime.now();
            if (this.dueDate.isEqual(today) && this.dueTime.isBefore(now)) {
                throw new BrockException("Due time cannot be earlier than now!");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getExtraInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String dueDateFormatted = dueDate
                .format(formatter);
        return "(by: " + dueDateFormatted
                + (dueTime == LocalTime.MAX
                ? ""
                : ", " + dueTime.toString())
                + ")";
    }
}
