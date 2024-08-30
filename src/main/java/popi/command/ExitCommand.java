package popi.command;

import popi.task.TaskList;
import popi.task.TaskManager;
import popi.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
    }
    @Override
    public void execute(TaskList tasks, Ui ui, TaskManager taskManager) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
