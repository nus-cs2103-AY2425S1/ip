package command;

import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        if (tasks.isTaskDone(taskIndex)) {
            ui.displayAlreadyMarked();
        } else {
            tasks.markTask(taskIndex);
            ui.displayMarkedTask(taskIndex, tasks);
            storage.save(tasks.getTasks());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
