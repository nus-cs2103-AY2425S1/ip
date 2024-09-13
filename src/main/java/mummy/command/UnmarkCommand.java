package mummy.command;

import java.util.HashMap;

import mummy.task.Task;
import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to unmark a task as done.
 * Inherits from the Command class.
 */
public final class UnmarkCommand extends Command {

    private Task unmarkedTask;

    public UnmarkCommand(HashMap<String, String> arguments) {
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
            taskList.unmarkTask(taskIndex);
            saveTaskListToStorage(taskList, storage);
            unmarkedTask = taskList.get(taskIndex);
            return "Nice! I've marked this task as not done yet:\n\t" + unmarkedTask;
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
        return CommandType.UNMARK;
    }

    @Override
    public String undo(TaskList taskList, Storage storage) throws MummyException {
        if (unmarkedTask == null) {
            throw new MummyException("Cannot find marked task");
        }

        Task task = unmarkedTask;
        unmarkedTask = null;

        task.setAsDone();
        return "Noted. I've marked this task as done:\n\t" + task;
    }
}
