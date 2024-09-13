package echoa;


/**
 * EchoaException is a class that encapsulates errors relating to Echoa.
 * It extends from the class Exception.
 */
public class EchoaException extends  Exception {

    String errorMessage;

    public EchoaException() {
        super();
    }

    public EchoaException(String s) {
        super();
        this.errorMessage = s;
    }

    @Override
    public String getMessage() {
        return errorMessage;
//        return "An error has occurred.\n";
    }
}
