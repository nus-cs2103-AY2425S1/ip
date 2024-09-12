package bob;

/**
 * Represents a custom exception class for Bob.
 */
class BobException extends Exception {

    /**
     * Constructor for BobException, which is a custom exception class for Bob.
     *
     * @param message
     */
    public BobException(String message) {
        super(message);
    }
}