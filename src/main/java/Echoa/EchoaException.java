package echoa;


/**
 * EchoaException is a class that encapsulates errors relating to Echoa.
 * It extends from the class Exception.
 */
public class EchoaException extends  Exception {
    public EchoaException() {
        super();
    }

    @Override
    public String getMessage() {
        return "An error has occurred.\n";
    }
}
