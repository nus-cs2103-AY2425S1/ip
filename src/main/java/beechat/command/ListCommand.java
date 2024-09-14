package beechat.command;

import beechat.util.Storage;
import beechat.task.TaskList;
import beechat.util.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }
}
