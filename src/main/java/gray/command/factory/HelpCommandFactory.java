package gray.command.factory;

import gray.GrayException;
import gray.command.Command;
import gray.command.HelpCommand;

/**
 * A parser that creates a command that provide user help.
 */
public class HelpCommandFactory extends CommandFactory {
    @Override
    public Command parse(String text) throws GrayException {
        if (!text.equals("help")) {
            return null;
        }
        return new HelpCommand();
    }
}
