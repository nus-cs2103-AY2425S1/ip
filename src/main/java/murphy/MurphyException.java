package murphy;

/**
 * Represents a subclass of checked exceptions which Murphy components can throw.
 */
public class MurphyException extends Exception {
    public MurphyException(String message) {
        super(message);
    }
}
