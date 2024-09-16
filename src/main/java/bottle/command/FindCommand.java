package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Task;
import bottle.task.TaskList;

/**
 * The type Find command.
 */
public class FindCommand extends Command {
    /**
     * The Filter string.
     */
    private final String filterString;

    /**
     * Instantiates a new Find command.
     *
     * @param filterString the filter string
     */
    public FindCommand(String filterString) {
        this.filterString = filterString;
    }
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
