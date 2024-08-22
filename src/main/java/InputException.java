/**
 * Represents an exception that is thrown when there is an issue with the user's input.
 * This exception can be used to indicate errors such as invalid format or missing information in the input.
 */
public class InputException extends Exception{

    /**
     * Constructs a new InputException with no detail message.
     * This can be used when the exception needs to be thrown without a specific error message.
     */
    public InputException() {}

    /**
     * Constructs a new InputException with the specified detail message.
     * This can be used to provide a specific error message when the exception is thrown.
     *
     * @param message the detail message providing more information about the error.
     */
    public InputException(String message) {
        super(message);
    }
}
