package mel.exceptions;

/**
 * Exception thrown for providing an
 * unknown or invalid input to Mel.
 */
public class MelException extends Exception {
    /**
     * Constructor for MelException.
     * @param input Mel's intended response to invalid input.
     */
    public MelException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
