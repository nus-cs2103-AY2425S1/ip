package yihuibot.ui.function;

import yihuibot.exception.parse.InvalidArgumentException;
import yihuibot.exception.parse.TooLittleArgumentsException;
import yihuibot.exception.parse.TooManyArgumentsException;
import yihuibot.executable.DeleteTask;
import yihuibot.executable.Executable;

/**
 * Process the arguments and return a suitable Executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class DeleteFunction extends Function {
    /**
     * Return a DeleteTask Executable if 'delete' is called with an integer. Otherwise,
     * throws ParseException.
     *
     * @param arguments the list of arguments called with 'delete'.
     * @return a DeleteTask Executable.
     * @throws TooLittleArgumentsException when 'delete' is not called with any arguments.
     * @throws TooManyArgumentsException when more than one argument is provided.
     * @throws InvalidArgumentException when the argument for 'delete' is not an integer.
     */
    @Override
    public Executable call(String... arguments) throws TooLittleArgumentsException,
            TooManyArgumentsException, InvalidArgumentException {
        String sample = "delete 2";

        if (arguments == null) {
            throw new TooLittleArgumentsException(1, sample);
        }

        if (arguments.length > 1) {
            throw new TooManyArgumentsException(1, sample);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new DeleteTask(idx);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException("int", arguments[0]);
        }
    }
}
