package luna.command;

import java.util.ArrayList;

import luna.Storage;
import luna.TaskList;

import luna.LunaException;
import luna.task.Task;

public class UnmarkCommand extends Command {
    private final int taskToUnmark;

    public UnmarkCommand(int taskToUnmark) {
        this.taskToUnmark = taskToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws LunaException {
        ArrayList<Task> unmarked = tasks.unmarkTask(taskToUnmark);
        storage.saveTasks(unmarked);
    }
}
