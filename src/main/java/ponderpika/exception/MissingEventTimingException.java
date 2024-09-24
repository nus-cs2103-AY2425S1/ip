package ponderpika.exception;

/**
 * Exception thrown when the timing information (both "From" and "To" times) is missing for an event task.
 * <p>
 * This class extends PonderPikaException to handle specific cases
 * where event timing details are required but not provided.
 * </p>
 */
public class MissingEventTimingException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that the "From" and "To" times for the event task are missing.
     *
     * @return A string that represents the exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Missing \"From\" and \"To\" times for Event task!");
    }
}
