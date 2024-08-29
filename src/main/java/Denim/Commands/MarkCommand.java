package denim.commands;

import denim.exceptions.DenimException;
import denim.TaskList;
import denim.storage.TaskIO;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_USAGE = "mark <taskNumber>";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(TaskList taskList, TaskIO taskIO) {

        if (!taskList.isValidIndex(index)) {
            return new CommandResult("The index chosen is invalid.");
        }

        boolean alreadyMarked = taskList.getTask(index).getIsDone();
        if (alreadyMarked) {
            return new CommandResult("The task is already marked.");
        }

        try {
            taskList.markTask(index);
            taskIO.markTask(taskList);
        } catch (DenimException e) {
            taskList.unmarkTask(index);
            return new CommandResult(e.getMessage());
        }

        String returnMessage = String.format("Okay, I've marked this task as done: \n %s\n", taskList.getTask(index));
        return new CommandResult(returnMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
