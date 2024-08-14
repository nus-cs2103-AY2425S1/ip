package sigma.exception;

/**
 * Represents the base class for all exceptions in the Sigma application.
 * Provides a general error message format.
 */
public abstract class SigmaException extends Exception {
    /**
     * Returns a basic error message prefix.
     *
     * @return a string representing the error message prefix
     */
    @Override
    public String toString() {
        return "Error:";
    }
}
