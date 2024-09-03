package sammy.command;

import sammy.Storage;
import sammy.TaskList;
import sammy.Ui;
import sammy.Command;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}

