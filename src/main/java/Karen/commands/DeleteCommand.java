package Karen.commands;

import Karen.tasks.Task;
import Karen.tasks.TaskList;
import Karen.util.Storage;
import Karen.util.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task t = taskList.getTask(this.index);
        taskList.deleteTask(this.index);
        ui.showDeleteMessage(t);
        Storage.saveToFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}