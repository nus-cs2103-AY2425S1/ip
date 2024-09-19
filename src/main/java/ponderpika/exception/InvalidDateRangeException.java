package ponderpika.exception;

/**
 * Exception thrown when an invalid date range is specified for an event task.
 * <p>
 * This exception extends the PonderPikaException class and is used to indicate
 * that the date range provided for an event task is incorrect or invalid
 * </p>
 */
public class InvalidDateRangeException extends PonderPikaException {

    /**
     * Returns a string representation of the exception which includes
     * the default message from the superclass along with a specific message
     * stating that the date range is incorrect for the event task
     *
     * @return A string representation of the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Date Range is incorrect for event task!");
    }
}
