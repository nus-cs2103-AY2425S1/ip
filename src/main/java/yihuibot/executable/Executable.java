package yihuibot.executable;

import yihuibot.exception.executable.ExecutableException;

/**
 * A class for all executables by YihuiBot.
 *
 * @author Toh Yi Hui A0259080A
 */
public interface Executable {
    /**
     * Executes the executable and store the output.
     *
     * @return true if the program should exit. False otherwise.
     * @throws ExecutableException when an error occurs during execute.
     */
    public abstract boolean execute() throws ExecutableException;

    /**
     * Return the output of the executable.
     *
     * @return the output of the executable.
     */
    public abstract String getOutput();
}
