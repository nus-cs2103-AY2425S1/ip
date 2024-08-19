package executable;

/**
 * An exit executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Exit implements Executable {
    private String output;

    /**
     * Output an exit message.
     *
     * @return TERMINATE.
     */
    @Override
    public Executable.exitCode execute() {
        output = "Bye. Hope to see you again soon!";
        return Executable.exitCode.TERMINATE;
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
