package Revir.User.Command;

import Revir.Tasks.TaskList;
import Revir.User.Ui;

public class Nop extends Command {
    public Nop(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        return;
    }
}
