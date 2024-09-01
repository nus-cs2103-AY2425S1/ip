package yihuibot.executable;

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
     * @return true.
     */
    @Override
    public boolean execute() {
        output = "Bye. Hope to see you again soon!\nSaving tasks to file.";
        return true;
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
