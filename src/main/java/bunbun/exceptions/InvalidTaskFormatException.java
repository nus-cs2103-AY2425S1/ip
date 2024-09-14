package bunbun.exceptions;

/**
 * This class implements an invalid task format exception.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class InvalidTaskFormatException extends BunbunException {

    /**
     * Instantiates an invalid task format exception with the given message.
     *
     * @param msg String indicating message for exception.
     */
    public InvalidTaskFormatException(String msg) {
        super(msg);
    }
}
