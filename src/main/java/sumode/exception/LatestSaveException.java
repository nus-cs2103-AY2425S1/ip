package sumode.exception;

/**
 * An Exception thrown for unable to save latest changes.
 */
public class LatestSaveException extends SumoDException {

    /**
     * Constructor for LatestChangeException
     */
    public LatestSaveException() {
        super("Sumo cannot save latest change.\n Just like how you cannot save me from this problem");
    }
}
