package ponderpika.exception;

/**
 * Exception thrown when an invalid priority level is specified by the user
 * <p>
 * This class extends PonderPikaException to specifically handle cases
 * where the provided priority level does not exist.
 * </p>
 */
public class InvalidPriorityException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that no such priority level is available.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "No such priority level available!");
    }
}
