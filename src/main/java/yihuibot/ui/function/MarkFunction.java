package yihuibot.ui.function;

import yihuibot.exception.parse.InvalidArgumentException;
import yihuibot.exception.parse.TooLittleArgumentsException;
import yihuibot.exception.parse.TooManyArgumentsException;
import yihuibot.executable.Executable;
import yihuibot.executable.MarkTask;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class MarkFunction extends Function {
    /**
     * Return a MarkTask Executable if 'mark' is called with an integer. Otherwise,
     * throws ParseExceptions.
     *
     * @param arguments the list of arguments called with 'mark'.
     * @return a MarkTask Executable.
     * @throws TooLittleArgumentsException if 'mark' is not called with any arguments.
     * @throws TooManyArgumentsException when more than one argument is provided.
     * @throws InvalidArgumentException when the argument for 'mark' is not an integer.
     */
    @Override
    public Executable call(String... arguments) throws TooLittleArgumentsException,
            TooManyArgumentsException, InvalidArgumentException {
        String sample = "mark 2";

        if (arguments == null) {
            throw new TooLittleArgumentsException(1, sample);
        }

        if (arguments.length > 1) {
            throw new TooManyArgumentsException(1, sample);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new MarkTask(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("int", arguments[0]);
        }
    }
}
