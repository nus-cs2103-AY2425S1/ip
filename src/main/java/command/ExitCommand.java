package command;

import him.Ui;
import task.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList list) {
        return Ui.exit();
    }
}
