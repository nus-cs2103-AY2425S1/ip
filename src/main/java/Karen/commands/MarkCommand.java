package Karen.commands;

import Karen.tasks.Task;
import Karen.tasks.TaskList;
import Karen.util.Storage;
import Karen.util.Ui;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markTask(this.index);
        Task t = taskList.getTask(this.index);
        ui.showMarkMessage(t);
        Storage.saveToFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
