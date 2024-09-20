package meeju;

/**
 * MeejuException class represents a custom checked exception used in the Meeju application.
 *
 * This exception is typically thrown when there is an error specific to the application's logic, such as
 * invalid user input or failed IO operations. The exception includes a custom message that can be accessed
 * and displayed to the user.
 *
 */
public class MeejuException extends Exception {

    private String message;
    /**
     * Constructor for MeejuException.
     * MeejuException is thrown when the bot encounters exceptional situations.
     *
     * @param message The detail about the exception that occured.
     */
    public MeejuException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "Meow!! =^..^= " + message;
    }
}
