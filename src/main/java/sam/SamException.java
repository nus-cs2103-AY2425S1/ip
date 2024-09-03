package sam;

/**
 * The SamException class represents an exception that can be thrown in the Sam package.
 * It extends the Exception class.
 */
public class SamException extends Exception {
    /**
     * Constructs a new SamException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public SamException(String message) {
        super(message);
    }

    /**
     * Constructs a new SamException with a default detail message.
     * The default message is "Huh? I'm not quite sure what you mean by that."
     */
    public SamException() {
        super("Huh? I'm not quite sure what you mean by that.");
    }
}
