package bruno.exceptions;

/**
 * Exception should be thrown when a command is missing some fields.
 */
public class MissingFieldException extends BrunoException {
    public MissingFieldException() {
        super("Some information needed is missing in your command.");
    }
}
