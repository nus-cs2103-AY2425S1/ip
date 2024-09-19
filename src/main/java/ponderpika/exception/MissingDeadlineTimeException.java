package ponderpika.exception;

/**
 * Exception thrown when a task is missing a deadline time for a deadline task.
 * <p>
 * This class extends PonderPikaException to specifically address
 * errors related to missing deadline information.
 * </p>
 */
public class MissingDeadlineTimeException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with additional information
     * indicating that a task is missing a deadline time.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Your task is missing a deadline time");
    }
}
