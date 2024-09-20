package totoro.exception;

/**
 * Represents a custom exception used in the Totoro application
 * This is the base class for all custom exceptions in the application
 * Subclasses should provide specific exception types with detailed error messages
 */
public abstract class TotoroException extends Exception {
    /**
     * Returns a basic error message indicating that an error has occurred
     * Subclasses may override this method to provide more specific error details
     *
     * @return A string with the basic error message "Error: "
     */
    @Override
    public String toString() {
        return "Error: ";
    }
}
