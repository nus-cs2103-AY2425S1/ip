package king.commands;

import king.KingException;
import king.Storage;
import king.TaskList;
import king.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
            storage.save(tasks.getTaskList());
            ui.showTaskList(tasks);
        } else {
            ui.showError("Invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
