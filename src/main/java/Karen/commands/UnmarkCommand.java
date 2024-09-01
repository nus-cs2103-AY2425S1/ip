package Karen.commands;

import Karen.tasks.Task;
import Karen.tasks.TaskList;
import Karen.util.Storage;
import Karen.util.Ui;

public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.unmarkTask(this.index);
        Task t = taskList.getTask(this.index);
        ui.showUnmarkMessage(t);
        Storage.saveToFile(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
