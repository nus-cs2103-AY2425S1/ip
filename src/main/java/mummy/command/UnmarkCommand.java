package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to unmark a task as done.
 * Inherits from the Command class.
 */
public class UnmarkCommand extends Command {

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
            return "Nice! I've marked this task as not done yet:\n\t" + taskList.get(taskIndex);
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
}
