package Commands;

import Messages.ReturnMessage;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(String[] additionalInput) {
        super(additionalInput);
    }

    public MarkCommand(int task) {
        super(new String[] {});
        this.taskNumber = task;
    }

    @Override
    public ReturnMessage execute() {
        return new ReturnMessage();
    }
}
