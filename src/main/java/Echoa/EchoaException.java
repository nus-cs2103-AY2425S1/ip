package echoa;

/**
 * EchoaException is a class that encapsulates errors relating to Echoa.
 * It extends from Exception.
 */

public class EchoaException extends Exception {
    String errorMessage;
    public EchoaException() {
        super();
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
