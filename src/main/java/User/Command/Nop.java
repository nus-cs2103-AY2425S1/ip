package User.Command;

import Tasks.TaskList;
import User.Ui;

public class Nop extends Command {
    public Nop(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        return;
    }
}
