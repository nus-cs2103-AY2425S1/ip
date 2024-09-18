package james;

/**
 * Represents an exception thrown when a required identifier is missing or incorrect.
 * <p>
 * This exception is a specific type of JamesException used to indicate
 * that a task identifier or related information is incomplete or missing.
 * </p>
 */
class IncorrectIdentifierException extends JamesException {
    /**
     * Creates a new IncorrectIdentifierException with the specified message.
     *
     * @param message The detail message of the exception
     */
    public IncorrectIdentifierException(String message) {
        super(message);
    }
}