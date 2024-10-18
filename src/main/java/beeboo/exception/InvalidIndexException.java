package beeboo.exception;

/**
 * Represents an exception that is thrown when an invalid index is provided.
 * This exception is used to indicate that the specified index does not exist in the task list.
 */
public class InvalidIndexException extends BeeBooExceptions {

    /**
     * Constructs an InvalidIndexException with a specific error message.
     *
     * @param error The error message associated with this exception.
     */
    public InvalidIndexException(String error) {
        super(error);
    }

    /**
     * Returns a string representation of this exception, indicating that the index is invalid.
     *
     * @return A message stating that the index does not exist and prompting the user to check the task list.
     */
    @Override
    public String toString() {
        return "Index doesn't exist. Please input a valid index. You can use the \"list\" command to check your list.";
    }
}
