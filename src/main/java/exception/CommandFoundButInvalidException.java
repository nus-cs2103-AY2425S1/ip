package exception;

/**
 * Exception thrown when a command is found but is of invalid syntax
 */
public class CommandFoundButInvalidException extends Exception {

    /**
     * Constructs a {@code CommandFoundButInvalidException} with the specified detail message.
     *
     * @param input the message which is retrieved later when the exception is thrown
     */
    public CommandFoundButInvalidException(String input) {
        super(input);
    }
}
