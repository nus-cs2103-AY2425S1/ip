
package simon;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand implements Command {
    private final String name;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param name The keyword to search for in the tasks.
     */
    public FindCommand(String name) {
        this.name = name;

    }
    /**
     * Executes the find command, searching for tasks in the task list
     * that contain the specified keyword. The results are displayed using the UI.
     *
     * @param taskList The list of tasks to search through.
     * @param ui The UI object used to display the search results.
     * @param storage The storage object used for loading and saving tasks (not used in this command).
     * @return A string representing the search results.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.toArr();
        ArrayList<Task> results = new ArrayList<>();
        for (Task task:tasks) {
            if (task.getName().toLowerCase().contains(this.name.toLowerCase())) {
                results.add(task);
            }
        }
        return ui.showSearchTask(results);


    }
}
