package Karen.commands;

import Karen.tasks.TaskList;
import Karen.util.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
