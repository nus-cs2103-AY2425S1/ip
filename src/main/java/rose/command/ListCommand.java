package rose.command;

import rose.Storage;
import rose.TaskList;
import rose.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showTasks(ui);
    }
}
