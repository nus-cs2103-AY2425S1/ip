package exception;

/**
 * Exception thrown when a command is found but description is either of wrong syntax or empty
 */
public class CommandFoundButInvalidException extends Exception {

    /**
     * Constructs a {@code CommandFoundButInvalidException} with the specified detail message.
     *
     * @param input the command that was passed in
     */
    public CommandFoundButInvalidException(String input) {
        super(input);
    }
}
