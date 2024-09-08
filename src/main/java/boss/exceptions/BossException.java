package boss.exceptions;

/**
 * Represents the Boss Exception class.
 */

public class BossException extends Exception {

    /**
     * Creates a Boss Exception object
     *
     * @param message contains error message of exception
     */
    public BossException(String message) {
        super(message);

    }

    @Override
    public String toString() {
        return getMessage();
    }
}
