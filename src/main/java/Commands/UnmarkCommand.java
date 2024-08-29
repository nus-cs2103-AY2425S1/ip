package Commands;

import Messages.ReturnMessage;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
