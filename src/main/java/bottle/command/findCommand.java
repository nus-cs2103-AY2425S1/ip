package bottle.command;

import bottle.Storage;
import bottle.Ui;
import bottle.task.Task;
import bottle.task.TaskList;

public class findCommand extends Command{
    private final String filterString;

    public findCommand(String filterString) {
        this.filterString = filterString;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList filteredList = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.getTaskDesc().contains(filterString)) {
                filteredList.addTask(task);
            }
        }
        ui.printTaskList(filteredList);
    }
}
