public class SoraException extends IllegalArgumentException {
    public SoraException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid Command. Please Try Again. Reason: " + getMessage();
    }
}
