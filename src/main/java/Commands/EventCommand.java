package Commands;

import Messages.ReturnMessage;

public class EventCommand extends Command {
    public EventCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
