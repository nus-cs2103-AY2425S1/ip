package main.java.angel;

/**
 * Represents an exception that is thrown by the Angel application.
 * This exception is used for general errors that occur within the application.
 */
public class AngelException extends Exception {

    /**
     * Constructs a new AngelException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval
     *                by the {@link #getMessage()} method.
     */
    public AngelException(String message) {
        super(message);
    }
}

