package torne.exception;

public class TorneInvalidDataException extends TorneException {
    public TorneInvalidDataException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid data: " + getMessage();
    }
}
