package snowy.common;

import snowy.data.SnowyException;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task as done.
     *
     * @return a CommandResult indicating the task has been marked as done
     * @throws SnowyException if the task is already done or if there is an error marking it
     */
    @Override
    public CommandResult execute() throws SnowyException {
        if (taskList.isTaskDone(index - 1)) {
            throw new SnowyException("Task is already done");
        }
        String str = taskList.toggleTask(index - 1);
        return new CommandResult(str + "\nMarked task as done at index " + index);
    }
}
