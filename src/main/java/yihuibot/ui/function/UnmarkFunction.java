package yihuibot.ui.function;

import yihuibot.exception.parse.InvalidArgumentException;
import yihuibot.exception.parse.TooLittleArgumentsException;
import yihuibot.exception.parse.TooManyArgumentsException;
import yihuibot.executable.Executable;
import yihuibot.executable.UnmarkTask;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class UnmarkFunction extends Function {
    /**
     * Return an UnmarkTask Executable if 'unmark' is called with an integer. Otherwise,
     * throws ParseException.
     *
     * @param arguments the list of arguments called with 'unmark'.
     * @return a UnmarkTask Executable.
     * @throws TooLittleArgumentsException when 'unmark' is not called with any arguments.
     * @throws TooManyArgumentsException when more than one argument is provided.
     * @throws InvalidArgumentException when the argument for 'unmark' is not an integer.
     */
    @Override
    public Executable call(String... arguments) throws TooLittleArgumentsException,
            TooManyArgumentsException, InvalidArgumentException {
        String sample = "unmark 2";

        if (arguments == null) {
            throw new TooLittleArgumentsException(1, sample);
        }

        if (arguments.length > 1) {
            throw new TooManyArgumentsException(1, sample);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new UnmarkTask(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("int", arguments[0]);
        }
    }
}
