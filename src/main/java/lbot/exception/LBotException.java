package lbot.exception;

/**
 * This is the abstract parent LBotException class.
 */
public abstract class LBotException extends Exception {
    public LBotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
