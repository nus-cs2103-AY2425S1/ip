package hana;

/**
 * Represents exception with a message.
 */
public class HanaException extends Exception{

    /**
     * Constructs a new HanaException with a message.
     *
     * @param message The description of the exception.
     */
    public HanaException(String message) {
        super(message);
    }
}
