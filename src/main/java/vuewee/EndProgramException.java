package vuewee;

/**
 * Throw to end program gracefully such as using "bye" command.
 */
public class EndProgramException extends RuntimeException {
    public EndProgramException() {
        super();
    }
}
