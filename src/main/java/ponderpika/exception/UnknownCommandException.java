package ponderpika.exception;

/**
 * Exception thrown when an unknown command is entered by user.
 * <p>
 * This class extends PonderPikaException to handle errors
 * related to unrecognized commands.
 * </p>
 */
public class UnknownCommandException extends PonderPikaException {

    /**
     * Returns a detailed message about the exception, which includes
     * the default message from the superclass and an indication that an unknown
     * command was entered.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "You have entered an unknown command");
    }
}
