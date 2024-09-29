package torne.exception;

public class TorneInvalidCommandException extends TorneException {
    public TorneInvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid command: " + getMessage();
    }
}
