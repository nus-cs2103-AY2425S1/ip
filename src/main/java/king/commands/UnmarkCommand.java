package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        try {
            tasks.markTaskAsUndone(taskIndex);
            ui.showTaskUnmarked(tasks.getTask(taskIndex));
            storage.save(tasks.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new KingException("Invalid list number entered!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

