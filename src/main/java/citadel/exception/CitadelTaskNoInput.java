package citadel.exception;

/**
 * Represents an exception that is thrown when a task is created without a description.
 * This exception extends {@link CitadelException}.
 */
public class CitadelTaskNoInput extends CitadelException {

    /**
     * Returns a string representation of this exception, which includes a message
     * indicating that the description of the task cannot be empty.
     *
     * @return A string message indicating that the description of the item cannot be empty.
     */
    @Override
    public String toString() {
        return super.toString()
                + "The description of the item cannot be empty.";
    }
}
