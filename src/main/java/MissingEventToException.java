public class MissingEventToException extends InputException {
    public MissingEventToException() {
        super("The Event needs a /to argument!");
    }
}
