package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

public class UnmarkCommand extends Command {
    private final int taskToUnmark;

    public UnmarkCommand(int taskToUnmark) {
        super();
        this.taskToUnmark = taskToUnmark;
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        try {
            Task unmarkedTask = taskLog.undoTask(taskToUnmark);
            storage.storeTasks(taskLog);
            return new String[] {"no worries, we'll circle back around to this.",
                    "\t" + unmarkedTask.toString()};
        } catch (RizzlerException e) {
            return new String[] {e.getMessage()};
        }
    }
}
