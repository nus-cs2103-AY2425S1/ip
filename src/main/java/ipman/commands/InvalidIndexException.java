package ipman.commands;

/**
 * Thrown to indicate that the index is out of the range of the
 * <code>TaskList</code>.
 * This is a convenience class for <code>IndexOutOfBoundsException</code>.
 *
 * @see IndexOutOfBoundsException
 */
public class InvalidIndexException extends IndexOutOfBoundsException {
    /**
     * Constructs an <code>InvalidIndexException</code> with the error message
     * indicating what is the expected range of index and actual given index.
     *
     * @param capacity size of the list
     * @param actual requested index of the 1-indexed list
     */
    public InvalidIndexException(int capacity, int actual) {
        super(String.format(
            "Invalid index for list. Expected a number from 1 to %d, got %d",
            capacity,
            actual
        ));
    }
}
