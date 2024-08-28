package echobot.command;

import echobot.exception.EchoBotException;

public class ExitCommand extends Command {
    public final static String COMMAND = "bye";

    @Override
    public CommandResponse execute() throws EchoBotException {
        return new CommandResponse(true);
    }
}
