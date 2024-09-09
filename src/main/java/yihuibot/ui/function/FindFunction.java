package yihuibot.ui.function;

import yihuibot.exception.parse.TooLittleArgumentsException;
import yihuibot.executable.Executable;
import yihuibot.executable.FilterTask;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class FindFunction extends Function {
    /**
     * Return a FilterTask Executable to filter the Tasks based on the arguments.
     *
     * @param arguments the list of arguments called with 'find'.
     * @return a FilterTask Executable.
     * @throws TooLittleArguments if no arguments are called with 'find'.
     */
    @Override
    public Executable call(String... arguments) throws TooLittleArgumentsException {
        if (arguments == null || arguments.length < 1) {
            throw new TooLittleArgumentsException(1);
        }
        String filter = String.join(" ", arguments);
        return new FilterTask(filter);
    }
}
