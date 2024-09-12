package orion.orionexceptions;

/**
 * Exception thrown when the event command format is incorrect.
 *
 * <p>
 * The expected format for an event command is:
 * <code>event &lt;description&gt; /from &lt;start time&gt; /to &lt;end time&gt;</code>.
 * </p>
 *
 * @author Pradyumna
 */
public class InvalidEventException extends OrionException {

    /**
     * Creates a new InvalidEventException with a message showing the invalid input
     * and the correct format for the event command.
     *
     * @param message the invalid input that triggered the exception.
     */
    public InvalidEventException(String message) {
        super("You wrote" + message + " . The correct usage is event [description] /from [start time] /to [end time]");
    }
}
