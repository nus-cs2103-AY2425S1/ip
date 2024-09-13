package mummy.command;

import java.util.HashMap;

import mummy.task.Task;
import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to mark a task as done.
 * Inherits from the Command class.
 */
public final class MarkCommand extends Command {

    private Task markedTask;

    public MarkCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MummyException {
        String taskIndexString = this.getArgument("description");

        if (taskIndexString == null) {
            throw new MummyException("Task number is required");
        }

        int taskIndex = Parser.parseIntOrDefault(
                this.getArgument("description", ""),
                -1);

        try {
            taskList.markTask(taskIndex);
            saveTaskListToStorage(taskList, storage);
            markedTask = taskList.get(taskIndex);
            return "Nice! I've marked this task as done:\n\t" + markedTask;
        } catch (TaskListException exception) {
            throw new MummyException("Something went wrong when marking task as done: "
                    + exception.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MARK;
    }

    @Override
    public String undo(TaskList taskList, Storage storage) throws MummyException {
        if (markedTask == null) {
            throw new MummyException("Cannot find marked task");
        }

        Task task = markedTask;
        markedTask = null;

        task.setAsUndone();
        return "Noted. I've marked this task as not done yet:\n\t" + task;
    }
}
