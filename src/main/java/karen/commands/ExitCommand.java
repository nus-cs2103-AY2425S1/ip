package karen.commands;

import karen.tasks.TaskList;
import karen.util.Ui;

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
