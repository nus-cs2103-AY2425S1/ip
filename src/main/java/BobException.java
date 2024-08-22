public class BobException extends Exception {
    public BobException() {
        super();
    }

    public BobException(String message) {
        super(message);
    }

    public BobException(String message, Throwable cause) {
        super(message, cause);
    }

    public BobException(Throwable cause) {
        super(cause);
    }
}
