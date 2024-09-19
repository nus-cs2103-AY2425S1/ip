package snowy.common;

import snowy.data.SnowyException;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark the task as done.
     *
     * @return a CommandResult indicating the task has been unmarked
     * @throws SnowyException if the task is not done or if there is an error unmarking it
     */
    @Override
    public CommandResult execute() throws SnowyException {
        try {
            if (!taskList.isTaskDone(index - 1)) {
                throw new SnowyException("Cannot unmark task as it is not done");
            }
            String str = taskList.toggleTask(index - 1);
            return new CommandResult(str + "\nUnmarked task at index " + index);
        } catch (SnowyException e) {
            throw new SnowyException(e.getMessage());
        }
    }
}
