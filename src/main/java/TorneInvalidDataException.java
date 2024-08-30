public class TorneInvalidDataException extends TorneException {
    TorneInvalidDataException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid data: " + getMessage();
    }
}
