package bunbun.exceptions;

/**
 * This class implements an invalid date format exception.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class InvalidDateFormatException extends BunbunException {

    /**
     * Instantiates an invalid date format exception with the given message.
     *
     * @param msg String indicating message for exception.
     */
    public InvalidDateFormatException(String msg) {
        super(msg);
    }
}
