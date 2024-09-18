package mummy.command;

import java.util.HashMap;

import mummy.task.Task;
import mummy.task.TaskList;
import mummy.task.TaskListException;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents an abstract command to add a task.
 * This class extends the Command class and provides the implementation
 * for adding a task to the task list and saving it to storage.
 */
public abstract class AddCommand extends Command {

    public AddCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public final boolean isExit() {
        return false;
    }

    @Override
    public final CommandType getCommandType() {
        return CommandType.ADD;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     * @param taskList the task list to add the task to
     * @param storage the storage to save the task list
     * @return a string message which indicates the addition of the task and task list count
     * @throws MummyException if there is an error adding the task or saving the task list
     */
    public final String addTask(Task task, TaskList taskList, Storage storage) throws MummyException {
        taskList.add(task);
        saveTaskListToStorage(taskList, storage);
        return String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
                task, taskList.count()
        );
    }

    @Override
    public final String undo(TaskList taskList, Storage storage) throws MummyException {
        try {
            Task removedTask = taskList.remove(taskList.count());
            saveTaskListToStorage(taskList, storage);
            return String.format(
                    "Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n",
                    removedTask, taskList.count()
            );
        } catch (TaskListException exception) {
            throw new MummyException("Something went wrong when undoing command: "
                    + exception.getMessage()
            );
        }
    }
}
