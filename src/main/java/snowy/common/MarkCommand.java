package snowy.common;

import snowy.data.SnowyException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() throws SnowyException {
        if (taskList.isTaskDone(index - 1)) {
            throw new SnowyException("Task is already done");
        }
        taskList.toggleTask(index - 1);
        return new CommandResult("Marked task as done at index " + index);
    }
}
