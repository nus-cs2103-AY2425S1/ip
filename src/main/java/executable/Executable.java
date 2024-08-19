package executable;

/**
 * A class for all executables by YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public interface Executable {
    /**
     * Executes the executable and store the output.
     *
     * @return the exit code after executing the executable. In general, it should return:
     *         0 if there are no issues.
     *         1 if the bot should be terminated (e.g. after a terminating executable).
     *         2 if there are any unexpected errors encountered when executing.
     */
    public abstract int execute();

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    public abstract String getOutput();
}
