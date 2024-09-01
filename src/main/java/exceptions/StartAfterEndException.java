package exceptions;

/**
 * Thrown when there is an attempt to instantiate an event which starts after it ends.
 *
 * @author IsaacPangTH
 */
public class StartAfterEndException extends HimException {

    /**
     * Constructor for<code>StartAfterEndException</code>.
     */
    public StartAfterEndException() {
        super("Events need to end after they start!");
    }
}
