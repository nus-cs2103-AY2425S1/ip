package denim.commands;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.storage.TaskIo;

/**
 * Represents an unmark command that can be executed.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_USAGE = "unmark <taskNumber>";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }


    @Override
    public CommandResult execute(TaskList taskList, TaskIo taskIo) {

        if (!taskList.isValidIndex(index)) {
            return new CommandResult("The index chosen is invalid.");
        }

        boolean alreadyMarked = taskList.getTask(index).getIsDone();
        if (!alreadyMarked) {
            return new CommandResult("The task is already unmarked.");
        }

        try {
            taskList.unmarkTask(index);
            taskIo.unmarkTask(taskList);
        } catch (DenimException e) {
            taskList.markTask(index);
            return new CommandResult(e.getMessage());
        }

        String returnMessage = String.format("Okay, I've marked this task as not done yet: "
                + "\n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
