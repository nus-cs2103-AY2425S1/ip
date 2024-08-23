public class TorneInvalidCommandException extends TorneException {
    TorneInvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid command: " + getMessage();
    }
}
