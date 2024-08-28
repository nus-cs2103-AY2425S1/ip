package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.display();
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
