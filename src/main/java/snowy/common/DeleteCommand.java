package snowy.common;

import snowy.data.SnowyException;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws SnowyException {
        taskList.deleteTask(index);
        return new CommandResult("Deleted task at index " + index);
    }
}
