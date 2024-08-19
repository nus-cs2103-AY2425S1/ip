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
     * @return NORMAL.
     */
    @Override
    public Executable.exitCode execute() {
        return Executable.exitCode.NORMAL;
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
