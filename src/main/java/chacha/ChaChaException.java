package chacha;

/**
 * Represents a custom exception for ChaCha application.
 */
public class ChaChaException extends Exception {
    private String msg;

    /**
     * Constructs a new ChaChaException with a specified error message
     * @param msg The error message.
     */
    public ChaChaException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
