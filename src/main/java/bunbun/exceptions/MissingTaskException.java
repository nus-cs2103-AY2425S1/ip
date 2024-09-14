package bunbun.exceptions;

/**
 * This class implements an missing task exception.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class MissingTaskException extends BunbunException {

    /**
     * Instantiates a missing task exception with the given message.
     *
     * @param msg String indicating message for exception.
     */
    public MissingTaskException(String msg) {
        super(msg);
    }
}
