package Tasks;

import Exceptions.BrockException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class representing a deadline task.
 */
public class Deadlines extends Task {
    private final LocalDate DUE_DATE;
    private final LocalTime DUE_TIME;

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
        return LocalTime.of(Integer.parseInt(hours)
                , Integer.parseInt(minutes));
    }

    /**
     * Checks if the due date is valid.
     * If due time is provided (ie: not a dummy), checks if it is valid.
     *
     * @throws BrockException If they are not valid.
     */
    private void validateDateTime() throws BrockException {
        LocalDate today = LocalDate.now();
        if (DUE_DATE.isBefore(today)) {
            throw new BrockException("Due date cannot be earlier than today!");
        }
        if (DUE_TIME != LocalTime.MAX) {
            LocalTime now = LocalTime.now();
            if (DUE_TIME.isBefore(now)) {
                throw new BrockException("Due time cannot be earlier than now!");
            }
        }
    }

    /**
     * Sets the deadline task description and due date.
     * A dummy value is given for due time.
     * Sets the deadline task status to be uncompleted.
     *
     * @param description Task description.
     * @param dueDateString Task due date.
     * @throws BrockException If due date is invalid.
     */
    public Deadlines(String description, String dueDateString) throws BrockException {
        super(description);
        try {
            DUE_TIME = LocalTime.MAX; // dummy value for time
            DUE_DATE = LocalDate.parse(dueDateString);
            validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
        }
    }

    /**
     * Sets the deadline task description, due date and time.
     * Sets the deadline task status to be uncompleted.
     *
     * @param description Task description.
     * @param dueDateString Task due date.
     * @param dueTimeString Task due time.
     * @throws BrockException If due date or time is invalid.
     */
    public Deadlines(String description, String dueDateString, String dueTimeString) throws BrockException {
        super(description);
        try {
            DUE_TIME = parseTime(dueTimeString);
            DUE_DATE = LocalDate.parse(dueDateString);
            validateDateTime();
        } catch (DateTimeParseException e) {
            throw new BrockException("Values in due date string are not valid!");
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
        String dueDateFormatted = DUE_DATE
                .format(formatter);
        return "(by: " + dueDateFormatted
                + (DUE_TIME == LocalTime.MAX
                ? ""
                : ", " + DUE_TIME.toString())
                + ")";
    }
}
