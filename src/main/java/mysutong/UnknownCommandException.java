package mysutong;

/**
 * Exception thrown when the MySutong application encounters an unrecognized command.
 * Extends {@link SutongException} to handle application-specific exceptions related to command processing.
 */
class UnknownCommandException extends SutongException {

    /**
     * Constructs a new UnknownCommandException with the specified detail message.
     *
     * @param message the detail message, providing more information about why the exception was thrown.
     */
    public UnknownCommandException(String message) {
        super(message); // Pass the message up to the superclass constructor.
    }
}
