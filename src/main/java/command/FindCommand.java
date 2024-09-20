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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        return ui.displaySearchedTasks(tasks.getTasks(), search);
    }
}