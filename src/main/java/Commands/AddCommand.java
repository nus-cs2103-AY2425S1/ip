package Commands;

import Messages.ReturnMessage;

public class AddCommand extends Command {
    public AddCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
