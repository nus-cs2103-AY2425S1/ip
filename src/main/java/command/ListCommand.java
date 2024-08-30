package command;

import command.Command;
import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        ui.displayTasks(tasks.getTasks());
    }
}
