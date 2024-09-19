package darkpool.command;

import darkpool.DarkpoolException;
import darkpool.gui.Gui;
import darkpool.storage.Storage;
import darkpool.tasklist.TaskList;

/**
 * Represents a command to find tasks that contain a search query.
 */
public class FindCommand extends Command {
    private final String searchQuery;

    public FindCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        return taskList.search(searchQuery);
    }
}
