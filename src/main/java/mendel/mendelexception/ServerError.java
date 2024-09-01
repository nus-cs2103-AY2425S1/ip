package mendel.mendelexception;

/**
 * Represents a server-related error in the Mendel application.
 * Extends RuntimeException to provide specific error messages
 * related to internal server issues such as problems with database that may require the application to close.
 */
public class ServerError extends RuntimeException {
    /**
     * Constructs a new ServerError with a detailed message
     * indicating the internal server problem.
     *
     * @param message A description of the server error and the possible solutions.
     */
    public ServerError(String message) {
        super(String.format("Internal Server Error: %s\nApplication will be closed", message));
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return The detail message string of this {@code ServerError}.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
