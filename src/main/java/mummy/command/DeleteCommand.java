package mummy.command;

import java.util.HashMap;

import mummy.task.Task;
import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.utility.Storage;


/**
 * Represents a command to delete a task from the task list.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> arguments) {
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
            Task task = taskList.remove(taskIndex);
            saveTaskListToStorage(taskList, storage);
            return String.format(
                    "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
                    task, taskList.count()
            );
        } catch (TaskListException exception) {
            throw new MummyException("Something went wrong when deleting task: "
                    + exception.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE;
    }
}
