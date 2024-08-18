package executable;

/**
 * A class for all executables by YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public abstract class Executable {
    private String output;

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
     * Prints out the output. This print method cannot be override by subclasses of
     * executable as the output needs to follow the format as indicated here.
     */
    public final void print() {
        String wrapped = wrapStringWithHorizontalLines(output);
        System.out.println(wrapped);
    }

    /**
     * Executes the executable and print the output in a single method.
     * 
     * @return the exit code after executing the executable.
     *         Refer to the individual Executables to see the list of available
     *         return values.
     */
    public final int executeAndPrint() {
        int exitCode = execute();
        print();
        return exitCode;
    }

    /**
     * Change the output of the executable.
     *
     * @param output the changed output.
     */
    public void output(String output) {
        this.output = output;
    }

    private String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
    }
}
