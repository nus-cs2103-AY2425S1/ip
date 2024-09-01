package blue.Exceptions;

/**
 * Thrown to indicate that a user has provided an invalid item number, such as a task number that is out of range.
 */
public class WrongNumberOfItemException extends Exception {

    /**
     * Constructs a {@code WrongNumberOfItemException} with a detail message indicating the number of items in the list.
     *
     * @param n The number of items in the list.
     */
    public WrongNumberOfItemException(int n) {
        super("erm did you know we only have " + n + " items in the list");
    }
}
