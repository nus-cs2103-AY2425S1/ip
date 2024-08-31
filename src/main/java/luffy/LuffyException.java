package luffy;

/**
 * Represents an exception in the Luffybot
 */
public class LuffyException extends Exception {

    /**
     * Constructor to create a LuffyException object with no message
     */
    public LuffyException() {
        super();
    }

    /**
     * Constructor to create a LuffyException object with a message
     */
    public LuffyException(String message) {
        super(message);
    }
}
