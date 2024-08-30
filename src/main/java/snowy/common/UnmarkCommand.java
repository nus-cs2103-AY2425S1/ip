package snowy.common;

import snowy.data.SnowyException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws SnowyException {
        if (!taskList.isTaskDone(index - 1)) {
            throw new SnowyException("Cannot unmark task as it is not done");
        }
        taskList.toggleTask(index - 1);
        return new CommandResult("Unmarked task at index " + index);
    }
}
