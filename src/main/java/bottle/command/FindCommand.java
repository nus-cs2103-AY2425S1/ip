package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Task;
import bottle.task.TaskList;

/**
 * Represents a command to find tasks based on a filter string.
 */
public class FindCommand extends Command {
    /**
     * The filter string used to search for tasks.
     */
    private final String filterString;

    /**
     * Instantiates a new Find command with the specified filter string.
     *
     * @param filterString the string used to filter tasks
     */
    public FindCommand(String filterString) {
        this.filterString = filterString;
    }

    /**
     * Executes the find command, searching for tasks that contain the filter string in their description.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for saving tasks
     * @return a string representation of the filtered task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.getTaskDesc().contains(filterString)) {
                filteredList.addTask(task);
            }
        }
        assert filteredList != null : "Filtered List cannot be null!";
        return ui.printTaskList(filteredList);
    }
}
