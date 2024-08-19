package executable;

/**
 * A class for all executables by YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public interface Executable {
    public enum exitCode {NORMAL, TERMINATE, ERROR};

    /**
     * Executes the executable and store the output.
     *
     * @return the exit code after executing the executable. In general, it should return:
     *         NORMAL if there are no issues.
     *         TERMINATE if the bot should be terminated (e.g. after a terminating executable).
     *         ERROR if there are any unexpected errors encountered when executing.
     */
    public abstract exitCode execute();

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    public abstract String getOutput();
}
