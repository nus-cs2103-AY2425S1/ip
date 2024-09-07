package darkpool.command;

import darkpool.util.DarkpoolException;
import darkpool.util.Storage;
import darkpool.util.TaskList;
import darkpool.gui.Gui;

/**
 * Represents a command to find tasks in the task list.
 */
public class FindCommand extends Command {
    private final String searchQuery;

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param searchQuery The query to search for.
     */
    public FindCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Executes the find command, searching for tasks in the task list and updating the UI with the results.
     *
     * @param taskList The task list to search in.
     * @param gui       The UI to update with the search results.
     * @param storage  The storage to save any changes (if applicable).
     * @return
     * @throws DarkpoolException If an error occurs during the search.
     */
    @Override
    public String execute(TaskList taskList, Gui gui, Storage storage) throws DarkpoolException {
        return taskList.search(searchQuery);
    }
}
