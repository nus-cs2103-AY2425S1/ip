package sammy.command;

import sammy.Storage;
import sammy.task.TaskList;
import sammy.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

