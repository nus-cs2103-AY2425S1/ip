package bunbun.exceptions;

/**
 * This class implements an invalid command format exception.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class InvalidCommandFormatException extends BunbunException {

    /**
     * Instantiates an invalid command format exception with the given message.
     *
     * @param msg String indicating message for exception.
     */
    public InvalidCommandFormatException(String msg) {
        super(msg);
    }
}
