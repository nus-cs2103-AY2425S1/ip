package monique.exception;

/**
 * The <code>MoniqueException</code> class is an abstract base class for all custom exceptions
 * in the Monique application. It extends the built-in <code>Exception</code> class.
 * This class is used to represent errors specific to the Monique application.
 */
public abstract class MoniqueException extends Exception {

    /**
     * Constructs a new <code>MoniqueException</code> with the specified detail message.
     *
     * @param message The detail message to be associated with this exception.
     */
    public MoniqueException(String message) {
    }

    /**
     * Provides advice on how to handle this exception.
     * Subclasses are expected to implement this method to give specific instructions
     * or tips to the user on how to resolve the exception.
     */
    public abstract void advice();
}
