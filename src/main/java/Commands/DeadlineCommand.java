package Commands;

import Messages.ReturnMessage;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
