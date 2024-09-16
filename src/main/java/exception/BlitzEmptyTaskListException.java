package exception;

/**
 * Represents an exception where an operation is attempted on an empty TaskList.
 */
public class BlitzEmptyTaskListException extends BlitzException {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates nothing in the list.
     */
    @Override
    public String toString() {
        return "There is nothing in the list!";
    }
}
