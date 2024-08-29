package Commands;

import Messages.ReturnMessage;

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
