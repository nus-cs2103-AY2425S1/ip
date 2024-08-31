package exception;

public class ElliotException extends Exception {

    public ElliotException() {
        super();
    }

    public ElliotException(String message) {
        super(message);
    }

    public ElliotException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElliotException(Throwable cause) {
        super(cause);
    }

}
