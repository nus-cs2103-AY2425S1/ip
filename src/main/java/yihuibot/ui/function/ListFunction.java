package yihuibot.ui.function;

import yihuibot.exception.parse.TooManyArgumentsException;
import yihuibot.executable.Executable;
import yihuibot.executable.ListTask;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class ListFunction extends Function {
    /**
     * Return a ListTask Executable if there no arguments are provided. Throws
     * TooManyArgumentsException when 'list' is called with arguments.
     *
     * @param arguments the list of arguments called with 'list'.
     * @return a ListTask Executable.
     * @throws TooManyArgumentsException when command is called with any arguments.
     */
    @Override
    public Executable call(String... arguments) throws TooManyArgumentsException {
        if (arguments != null) {
            throw new TooManyArgumentsException(0);
        }
        return new ListTask();
    }
}
