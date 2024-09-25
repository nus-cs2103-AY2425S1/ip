package bangmang.command;

import bangmang.exception.InvalidTaskFormatException;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Task;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.storage.Storage;

import java.util.ArrayList;

/**
 * Represents a command to search for tasks containing the specified keyword.
 */

public class FindCommand extends Command {
    private String searchInput;

    /**
     * Constructs a FindCommand with the specified search input.
     *
     * @param searchInput The keyword to search for in task descriptions.
     */
    public FindCommand(String searchInput) {
        this.searchInput = searchInput;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     *
     * @param tasks The list of tasks to search within.
     * @param ui The UI instance for displaying messages.
     * @param storage The storage instance (not used in this command).
     * @return The search results message to be displayed.
     * @throws InvalidCommandException If any error occurs while searching tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        ArrayList<Task> searchResults = new ArrayList<>();

        try {
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.getDescription().contains(this.searchInput)) {
                    searchResults.add(t);
                }
            }
            return ui.showSearchResults(searchResults);
        } catch (InvalidTaskFormatException e) {
            throw new InvalidCommandException("Alamak, task number out of range. Please provide a valid task number.");
        }
    }

    /**
     * Returns false as this command does not terminate the application.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
