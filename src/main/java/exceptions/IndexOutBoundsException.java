package exceptions;

/**
 * The IndexOutBoundsException is thrown when the Index is out of bounds.
 * This exception is typically used in commands that require Integer input.
 */
public class IndexOutBoundsException extends IndexOutOfBoundsException {

    /**
     * Constructs a new IndexOutBoundsException with the specified detail message.
     */
    public IndexOutBoundsException() {
        super("Input a valid task number");
    }
}
