package easton.exception;

/**
 * Exception for an invalid input to be parsed.
 */
public class InvalidParseInputException extends Exception {
    /**
     * Constructs a new exception with a specified detail message.
     *
     * @param input Input that was given.
     * @param className Name of the class that was to be parsed.
     */
    public InvalidParseInputException(String input, String className) {
        super(input + " is an invalid input to be parsed to " + className);
    }
}
