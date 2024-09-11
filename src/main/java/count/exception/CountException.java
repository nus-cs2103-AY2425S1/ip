package count.exception;

/**
 * CountException is the class of Exceptions thrown by Count
 * It holds a message for Count to inform the user about the Exception
 */
public class CountException extends Exception {
    private String msg;

    /**
     * Constructor for CountException
     * @param msg String message to be printed
     */
    public CountException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return this.msg;
    }
}
