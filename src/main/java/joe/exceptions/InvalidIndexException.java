package joe.exceptions;

public class InvalidIndexException extends RuntimeException {
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
        return String.format("\"%d\" is not a valid index.\n"
                + "Type <list> to see the list of available indexes.", index);
    }
}
