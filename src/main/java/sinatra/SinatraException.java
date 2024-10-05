package sinatra;

/**
 * Represents an exception specific to the Sinatra application.
 */
public class SinatraException extends Exception {

    /**
     * Constructs a new SinatraException with the specified detail message.
     *
     * @param message the detail message
     */
    public SinatraException(String message) {
        super(message);
    }
}
