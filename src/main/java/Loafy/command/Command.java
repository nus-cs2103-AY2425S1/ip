package loafy.command;

import loafy.tasklist.TaskList;
import loafy.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui);

    public boolean isExit() {
        return false;
    }
}
