package commands;

import main.TaskList;
import storage.Storage;
import ui.Ui;
import tasks.Task;

/**
 * Represents a command that finds tasks containing a specified keyword in their descriptions.
 * This command searches through a task list and returns tasks that match the search term.
 */
public class FindCommand extends Command {
    private final String searchable;
    private final TaskList results;

    /**
     * Constructs a {@code FindCommand} with the specified search keyword.
     *
     * @param searchable The keyword to search for in the task descriptions.
     */
    public FindCommand(String searchable) {
        this.searchable = searchable;
        this.results = new TaskList();
    }

    /**
     * Executes the find command by searching through the task list for tasks whose descriptions
     * contain the search keyword. The found tasks are then displayed to the user through the UI.
     *
     * @param taskList The list of tasks to search through.
     * @param ui       The UI to interact with the user.
     * @param storage  The storage object (not used in this command but required by the method signature).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        for (Task t : taskList.getTasks()) {
            if (t.getDescription().contains(searchable)) {
                results.addTask(t);
            }
        }
        ui.showFound(this.results);
    }

}
