package revir.user.command;

import revir.tasks.TaskList;
import revir.user.Ui;

public class Nop extends Command {
    public Nop(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        return;
    }
}
