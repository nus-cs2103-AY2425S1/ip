package ponderpika.exception;

/**
 * Exception thrown when a required description for a task is missing.
 * <p>
 * This class extends the PonderPikaException to provide specific
 * error handling for the case where a task description is not provided.
 * </p>
 */
public class MissingDescriptionException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass and a specific message indicating
     * that task description is missing.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Your task is missing a description");
    }
}
