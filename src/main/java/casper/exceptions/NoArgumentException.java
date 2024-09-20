package casper.exceptions;

/**
 * Thrown to indicate that no arguments were provided for a command.
 * This exception provides a suggested solution for supplying the necessary arguments.
 */
public class NoArgumentException extends ExceptionWithSolution {
    /**
     * Constructs a new {@code NoArgumentException} with the specified command.
     *
     * @param command The command for which no arguments were provided.
     */
    public NoArgumentException(String command) {
        super(String.format("No arguments for %s.", command),
                "Please provide the necessary arguments.");
    }
}
