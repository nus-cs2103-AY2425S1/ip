package gray.command.factory;

import gray.GrayException;
import gray.command.Command;

/**
 * A parser that creates a command.
 */
public abstract class CommandFactory {

    /**
     * Returns the command parsed from the text.
     *
     * @param text
     * @return Command
     * @throws GrayException
     */
    public abstract Command parse(String text) throws GrayException;
}
