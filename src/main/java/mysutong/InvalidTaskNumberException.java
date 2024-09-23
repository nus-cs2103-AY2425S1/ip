package mysutong;

/**
 * Exception thrown when an invalid task number is used in operations that require a valid task index.
 * Extends {@link SutongException}.
 */
class InvalidTaskNumberException extends SutongException {

    /**
     * Constructs a new InvalidTaskNumberException with the specified detail message.
     *
     * @param message the detail message, providing more information about the exception context.
     */
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}
