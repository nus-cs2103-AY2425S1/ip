package Commands;

import Messages.ReturnMessage;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
