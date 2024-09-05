package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui) {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
