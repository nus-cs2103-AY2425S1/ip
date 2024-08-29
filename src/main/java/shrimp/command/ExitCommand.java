package shrimp.command;

import shrimp.task.TaskList;
import shrimp.utility.Ui;

public class ExitCommand implements Command {

    @Override
    public void run(TaskList tasks, Ui ui) {
        ui.printExit();
    }
}
