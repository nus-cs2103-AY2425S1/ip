package arsenbot.command;

import arsenbot.storage.Storage;
import arsenbot.task.TaskList;
import arsenbot.ui.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
