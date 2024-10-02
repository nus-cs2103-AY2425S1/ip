package moimoi.util.task;

import moimoi.util.exception.InvalidPeriodException;

/**
 * Represents a period task.
 */
public class Period extends Task {

    protected double period;

    /**
     * Constructs a period task of the specified description and period (in hours).
     *
     * @param description Task description.
     * @param period Period of task (in hours).
     */
    public Period(String description, double period) throws InvalidPeriodException {
        super(TaskEnum.P, description);

        if (period <= 0) {
            throw new InvalidPeriodException();
        }

        this.period = period;
    }

    /**
     * Returns the String representation of the task for UI display.
     *
     * @return String representation of the task for UI display.
     */
    @Override
    public String stringUI() {
        return super.stringUI() + " (needs " + this.period + " hours)";
    }

    /**
     * Returns the String representation of the task for storage.
     *
     * @return String representation of the task for storage.
     */
    @Override
    public String stringStorage() {
        return super.stringStorage() + " | " + this.period;
    }

}
