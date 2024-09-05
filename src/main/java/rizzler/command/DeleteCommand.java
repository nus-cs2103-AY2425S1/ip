package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int commandToDelete) {
        super();
        this.taskToDelete = commandToDelete;
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        try {
            Task deletedTask = taskLog.deleteTask(taskToDelete);
            storage.storeTasks(taskLog);
            return new String[] {"sure hon, i'll remove this task from the list.",
                    "\t" + deletedTask,
                    "now we have " + taskLog.getNumTasks() + " tasks left to work on."};
        } catch (RizzlerException e) {
            return new String[]{e.getMessage()};
        }
    }
}
