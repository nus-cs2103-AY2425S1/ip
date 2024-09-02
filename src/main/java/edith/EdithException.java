package edith;

/**
 * EdithException is a custom exception class used in the EDITH chatbot application.
 * It extends IllegalArgumentException to provide specific error messages related to invalid arguments or instructions.
 */
public class EdithException extends IllegalArgumentException {
    /**
     * Constructs a new EdithException with the specified detail message.
     * The message is appended with a default instruction to provide a valid input.
     *
     * @param message The detail message, which is saved for later retrieval by the getMessage() method.
     */
    public EdithException(String message) {
        super(message + " Please provide a valid instruction with the correct relevant details.");
    }

    /**
     * Constructs a new EdithException with the specified detail message.
     * This constructor allows the creation of an exception with only the provided message
     * without appending any default message.
     *
     * @param message The detail message for the exception.
     * @param num An additional integer value for further use if needed.
     */
    public EdithException(String message, int num) {
        super(message);
    }
}