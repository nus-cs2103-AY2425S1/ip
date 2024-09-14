package darkpool.command;

import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.tasklist.TaskList;
import darkpool.gui.Gui;

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
