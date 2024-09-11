package Naega.Command;

import Naega.Command.Command;
import Naega.Storage.Storage;
import Naega.Task.TaskList;
import Naega.Ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
