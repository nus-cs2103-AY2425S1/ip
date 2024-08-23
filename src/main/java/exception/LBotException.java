package exception;

public abstract class LBotException extends Exception {
    public LBotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
