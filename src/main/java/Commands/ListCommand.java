package Commands;

import Messages.ReturnMessage;

public class ListCommand extends Command {
    public ListCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
