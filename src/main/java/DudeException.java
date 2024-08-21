public class DudeException extends Exception {
    public DudeException(String message) {
        super("Uh oh! " + message);
    }
}
