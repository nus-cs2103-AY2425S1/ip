package victor.commands;

import victor.messages.ReturnMessage;

public class ExitCommand extends Command {
    public ExitCommand(String[] additionalInput) {
        super(additionalInput);
    }

    public boolean isExit() {
        return true;
    }

    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
