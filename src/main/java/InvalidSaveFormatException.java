public class InvalidSaveFormatException extends BarneyException {
    public InvalidSaveFormatException(String message) {
        super("Invalid Save File Format: " + message);
    }
}
