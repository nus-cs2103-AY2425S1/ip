package command;

import exception.ParserException;

/**
 * Handle related issues to the command bye
 */
public class ByeCommand extends Command {
    /**
     * Gets a ByeCommand instance from command line
     * @param cmdline Command line read from user
     * @throws ParserException If wrong format found for the constructor
     */
    public ByeCommand(String cmdline) throws ParserException {
        if (!cmdline.equals("bye")) {
            throw new ParserException("Too much arguments for bye command");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
