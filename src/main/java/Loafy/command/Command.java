package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui);

    public boolean isExit() {
        return false;
    }
}
