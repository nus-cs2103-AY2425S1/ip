/**
 * Custom exception class for handling exceptions specific to the Regina chatbot application.
 * This class extends the built-in Exception class in Java.
 */
public class ReginaException extends Exception {

    /**
     * Constructs a new ReginaException with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the getMessage() method.
     */
    public ReginaException(String message) {
        super(message); // Call the constructor of the Exception class
    }
}
