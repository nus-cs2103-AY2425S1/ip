public class EndingTimeException extends YapperTimeException {
    public EndingTimeException(String message) {
        super("No ending time given " + message);
    }
}