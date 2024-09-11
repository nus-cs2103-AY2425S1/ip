package muffin;

/**
 * The MuffinException class represents a custom exception used within the Muffin application.
 * It is thrown to indicate errors specific to the application's command processing and task management.
 */
public class MuffinException extends Exception {
    public MuffinException(String msg) {
        super(msg);
    }
}
