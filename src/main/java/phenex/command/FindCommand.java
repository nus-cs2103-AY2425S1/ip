package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Task.TaskType;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * FindCommand class which encapsulates a Command which finds tasks.
 */
public class FindCommand extends Command {
    private String name;
    private TaskType taskType;

    public FindCommand() {
        this.name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        if (this.name == null) {
            return ui.printTaskList(taskList.findTasksOfType(taskType));
        } else if (taskType == null) {
            return ui.printTaskList(taskList.findTasksOfName(name));
        }
        TaskList matchingTasks = taskList.findTasksOfName(this.name).findTasksOfType(taskType);
        return ui.printTaskList(matchingTasks);
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }
}
