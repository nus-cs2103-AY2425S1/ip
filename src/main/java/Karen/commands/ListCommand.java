package Karen.commands;

import Karen.tasks.TaskList;
import Karen.util.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.displayTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
