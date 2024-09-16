package exception;
/**
 * Represents an exception where a duplicated task is added to the list.
 */
public class BlitzDuplicateTaskException extends BlitzException {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates there is a duplicated task in the list.
     */
    @Override
    public String toString() {
        return "The task is already in the list!";
    }
}
