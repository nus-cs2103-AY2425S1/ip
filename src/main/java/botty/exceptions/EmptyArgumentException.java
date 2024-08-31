package botty.exceptions;

/**
 * Exception thrown when argument is empty
 */
public class EmptyArgumentException extends BottyException {
    /**
     * Constructs a new {@code EmptyArgumentException} with a detail message including which argument was empty.
     * @param flag the argument that is empty.
     */
    public EmptyArgumentException(String flag) {
        super("The following argument is empty: " + flag);
    }
}
