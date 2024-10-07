package citadel.exception;

/**
 * Represents an exception that is thrown when an invalid argument is passed to a method.
 * Specifically, this exception is used when an index is out of bounds.
 * This exception extends {@link CitadelException}.
 */
public class CitadelInvalidArgException extends CitadelException {

    /**
     * Returns a string representation of this exception, which includes a message
     * indicating that the index is out of bounds.
     *
     * @return A string message indicating that the index is out of bounds.
     */
    @Override
    public String toString() {
        return super.toString()
                + "Index is out of bounds :(";
    }
}
