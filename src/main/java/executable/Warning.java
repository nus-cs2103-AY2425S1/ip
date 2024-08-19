package executable;

/**
 * An executable that displays a warning message. Does not do anything other than
 * store the message as output.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Warning implements Executable {
    private String output;

    /**
     * Constructor for a Warning executable.
     *
     * @param message the message of the warning.
     */
    public Warning(String message) {
        output = message;
    }

    /**
     * Does nothing.
     *
     * @return 0.
     */
    @Override
    public int execute() {
        return 0;
    }

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    @Override
    public String getOutput() {
        return output;
    }
}
