public class YapperFormatException extends YapperException {
    public YapperFormatException(String message) {
        super("Invalid format " + message);
    }
}
