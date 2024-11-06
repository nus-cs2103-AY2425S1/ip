package bunbun.exceptions;

/**
 * This class implements at ask number out-of-bounds exception.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class TaskNumOutOfBoundsException extends BunbunException {

    /**
     * Instantiates a task number out-of-bounds exception with the given message
     * when indicating a task number greater than the number of tasks or negative.
     *
     * @param msg String indicating message for exception.
     */
    public TaskNumOutOfBoundsException(String msg) {
        super(msg);
    }
    
}
