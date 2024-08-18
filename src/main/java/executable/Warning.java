package executable;

/**
 * An executable that displays a warning message. Does not do anything other than
 * store the message as output.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Warning extends Executable {
    /**
     * Constructor for a Warning executable.
     *
     * @param message the message of the warning.
     */
    public Warning(String message) {
        super.output(message);
    }

    /**
     * Does nothing.
     *
     * @return 0.
     */
    public int execute() {
        return 0;
    }
}
