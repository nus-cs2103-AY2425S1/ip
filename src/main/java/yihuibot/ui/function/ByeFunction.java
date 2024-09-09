package yihuibot.ui.function;

import yihuibot.exception.parse.TooManyArgumentsException;
import yihuibot.executable.Executable;
import yihuibot.executable.Exit;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class ByeFunction extends Function {
    /**
     * Return an Exit Executable if there no arguments are provided. Throws
     * TooManyArgumentsException when 'bye' is called with arguments.
     *
     * @param arguments the list of arguments called with 'bye'.
     * @return an Exit Executable.
     * @throws TooManyArgumentsException when command is called with any arguments.
     */
    @Override
    public Executable call(String... arguments) throws TooManyArgumentsException {
        if (arguments != null) {
            throw new TooManyArgumentsException(0);
        }
        return new Exit();
    }
}
