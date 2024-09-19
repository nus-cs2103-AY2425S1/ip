package ponderpika.exception;

/**
 * Exception thrown when a command is issued without specifying an index
 * <p>
 * for a task in a Pika context. This class extends PonderPikaException
 * to handle cases where a required index value is missing.
 * </p>
 */
public class MissingTaskIndexException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that the index value is not mentioned for the command.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Index value not mentioned for the command!");
    }
}
