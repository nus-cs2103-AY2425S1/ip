package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.showTaskList(tasks);
    }
}
