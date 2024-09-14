package mysutong;

/**
 * Custom exception class for the MySutong application, used to handle application-specific errors.
 * Extends {@link Exception}.
 */
class SutongException extends Exception {

    /**
     * Constructs a new SutongException with the specified detail message.
     *
     * @param message the detail message, providing more information about why the exception was thrown.
     */
    public SutongException(String message) {
        super(message);
    }
}
