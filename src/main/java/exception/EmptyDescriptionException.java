package exception;

/**
 * Exception thrown when a command is found but there is no description
 */
public class EmptyDescriptionException extends CommandFoundButInvalidException {

    /**
     * Constructs a {@code EmptyDescriptionException} with a detailed message
     * constructed from the input.
     *
     * @param input the command that was passed in
     */
    public EmptyDescriptionException(String input) {
        super(String.format("Bruh, you can't just type %s. Give me more details.", input));
    }
}
