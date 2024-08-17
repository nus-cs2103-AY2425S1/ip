public class StartingTimeException extends YapperTimeException {
    public StartingTimeException(String message) {
        super("No starting time given " + message);
    }
}
