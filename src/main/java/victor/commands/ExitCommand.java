package victor.commands;

import victor.messages.ReturnMessage;

/**
 * Exit command that extends command class. Only command to override isExit() method from
 * Command class to return true, which will trigger end of runUntilExit loop.
 */
public class ExitCommand extends Command {
    public ExitCommand(String[] additionalInput) {
        super(additionalInput);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
