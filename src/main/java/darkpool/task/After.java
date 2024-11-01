package darkpool.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import darkpool.DarkpoolException;

/**
 * Represents a task that starts after a certain time.
 */
public class After extends Task {

    protected LocalDateTime fromTime;

    /**
     * Constructor for After.
     *
     * @param description Description of the task.
     * @param fromTime    Time the task starts.
     * @throws DarkpoolException If the date is not in the correct format.
     */
    public After(String description, String fromTime, boolean isDone) throws DarkpoolException {
        super(description, isDone);

        try {
            this.fromTime = LocalDateTime.parse(fromTime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DarkpoolException("know what a date is? (dd-mm-yyyy hh:mm)");
        }
    }


    @Override
    public String toString() {
        return "[A]" + (isDone ? "[X] " : "[ ] ") + this.description
                + " (from:" + this.fromTime.format(FORMATTER) + ")";
    }


    @Override
    public String toFileString() {
        return ("A | " + (isDone ? "1" : "0") + " | " + this.description + " | "
                + this.fromTime.format(FORMATTER) + "\n");
    }

}
