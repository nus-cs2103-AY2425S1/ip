package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void actionable(TaskList taskList, Ui ui) {
        ui.printBye();
        ui.close();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
