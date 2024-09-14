package bunbun.exceptions;

/**
 * This class implements exceptions for Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */

abstract public class BunbunException extends Exception {

    /**
     * Instantiates a BunbunException with the given message.
     *
     * @param msg String indicating message for exception.
     */
    public BunbunException(String msg) {
        super(msg);
    }

}
