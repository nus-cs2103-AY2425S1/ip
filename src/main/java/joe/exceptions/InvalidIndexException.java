package joe.exceptions;

import java.lang.reflect.InvocationTargetException;

/**
 * Represents an exception when the user inputs an invalid index.
 */
public class InvalidIndexException extends InvocationTargetException {
    private final int index;

    /**
     * Constructor for InvalidIndexException.
     *
     * @param index an integer representing the invalid index
     */
    public InvalidIndexException(int index) {
        this.index = index;
    }

    /**
     * Returns a detailed message of the invalid index from the user
     * and how to look for the list of valid indexes
     *
     * @return a String message to help users
     */
    @Override
    public String getMessage() {
        return String.format("""
                "%d" is not a valid index.
                Type <list> to see the list of available indexes.""", index);
    }
}
