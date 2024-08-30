package command;

import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {

    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        if (!tasks.isTaskDone(taskIndex)) {
            ui.displayAlreadyUnmarked();
        } else {
            tasks.unmarkTask(taskIndex);
            ui.displayUnmarkedTask(taskIndex, tasks);
            storage.save(tasks.getTasks());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
