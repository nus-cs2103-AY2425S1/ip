package denim.commands;

import denim.TaskList;
import denim.exceptions.DenimException;
import denim.storage.WriteTaskFile;

/**
 * Represents a mark command that can be executed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_USAGE = "mark <taskNumber>";
    public static final String COMMAND_EXAMPLE = "mark 5";
    private int index;

    /**
     * Represents a mark command that can be executed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(TaskList taskList, WriteTaskFile writeTaskFile) {
        assert index >= 0 : "IndexOutOfBoundsAssertion";

        if (!taskList.isValidIndex(index)) {
            return new CommandResult("The index chosen is invalid. >:c", CommandStatus.COMMAND_PARTIAL_FAILURE);
        }

        boolean alreadyMarked = taskList.getTask(index).getIsDone();
        if (alreadyMarked) {
            return new CommandResult("The task is already marked. ;D", CommandStatus.COMMAND_PARTIAL_FAILURE);
        }

        try {
            taskList.markTask(index);
            writeTaskFile.markTask(taskList);
        } catch (DenimException e) {
            taskList.unmarkTask(index);
            return new CommandResult(e.getMessage(), CommandStatus.COMMAND_FAILURE);
        }

        String returnMessage = String.format("Okay, I've marked this task as done: \n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage, CommandStatus.COMMAND_SUCCESSFUL);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
