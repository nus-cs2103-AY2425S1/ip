public class MissingEventFromException extends InputException {
    public MissingEventFromException() {
        super("The Event needs a /from argument!");
    }
}
