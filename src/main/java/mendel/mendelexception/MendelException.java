package mendel.mendelexception;

/**
 * Custom exception class for the Mendel application.
 * Extends RuntimeException to provide specific error messages.
 */
public class MendelException extends RuntimeException {
    /**
     * Constructs a new @code MendelException with the specified detail message.
     *
     * @param message The detail message explaining the reason and solution for the exception.
     */
    public MendelException(String message) {
        super(message);
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return The detail message string of this.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
