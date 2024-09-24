package ponderpika.exception;

/**
 * Exception thrown when no keyword is provided for matching in a Pika context.
 * <p>
 * This class extends PonderPikaException to handle specific cases
 * where a required keyword is missing required for find command.
 * </p>
 */
public class MissingFindKeywordException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with additional information
     * stating that no keyword has been provided for matching for find command.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "No keyword provided to be matched");
    }
}
