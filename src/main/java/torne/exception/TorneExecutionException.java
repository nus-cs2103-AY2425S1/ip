package torne.exception;

public class TorneExecutionException extends TorneException {
    public TorneExecutionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Execution Error: " + getMessage();
    }
}
