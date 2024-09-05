package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerException;

public class MarkCommand extends Command {
    private final int taskToMark;

    public MarkCommand(int taskToMark) {
        super();
        this.taskToMark = taskToMark;
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        try {
            Task markedTask = taskLog.doTask(taskToMark);
            storage.storeTasks(taskLog);
            return new String[] {"aight, i'll note that you've completed this.",
                    "\t" + markedTask};
        } catch (RizzlerException e) {
            return new String[] {e.getMessage()};
        }
    }
}
