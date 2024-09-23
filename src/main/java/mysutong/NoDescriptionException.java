package mysutong;

/**
 * Exception thrown when a required description is missing for a task or operation.
 * Extends {@link SutongException}.
 */
class NoDescriptionException extends SutongException {

    /**
     * Constructs a new NoDescriptionException with the specified detail message.
     *
     * @param message the detail message, providing more information about why the exception was thrown.
     */
    public NoDescriptionException(String message) {
        super(message);
    }
}
