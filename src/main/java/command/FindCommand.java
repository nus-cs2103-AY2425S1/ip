package command;

import exceptions.BuddyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    private String search;

    public FindCommand(String search) {
        this.search = search;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        ui.displaySearchedTasks(tasks.getTasks(), search);
    }
}
