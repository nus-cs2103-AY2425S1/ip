package ontos.exception;

/**
 * Represents a custom exception in the Ontos application.
 * It extends the java.lang.Exception class, allowing for the creation of specific
 * exceptions that are relevant to the application's context.
 *
 * This exception should be used to signal issues specific to the Ontos application
 * that are not covered by standard Java exceptions.
 */
public class OntosException extends Exception {

    /**
     * Constructs a new OntosException with the specified detail message.
     *
     * @param message the detail message, saved for later retrieval by the getMessage() method.
     */
    public OntosException(String message) {
        super(message);
    }
}
