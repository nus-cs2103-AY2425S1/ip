package Commands;

import Messages.ReturnMessage;

public class ToDoCommand extends Command {
    public ToDoCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
    return new ReturnMessage();
    }
}
