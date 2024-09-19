package lama;

/**
 * Represents a custom checked exception specific to the Lama application.
 * This exception is thrown when the application encounters
 * an error that needs to be reported to the user.
 */
public class LamaException extends Exception {

    /**
     * Construct a LamaException with specified detailed of message.
     *
     * @param msg The detail of the message.
     */
    public LamaException(String msg) {
        super(msg);
    }

}
