package exception;

/**
 * Exception thrown when command is found, but syntax in invalid
 */
public class InvalidSyntaxException extends CommandFoundButInvalidException {

    /**
     * Constructs a {@code InvalidSyntaxException} with a detailed message
     * constructed from the input.
     *
     * @param input the command that was passed in
     */
    public InvalidSyntaxException(String input) {
        super(String.format("Uh Oh, wrong syntax for the command - %s", input));
    }
}
