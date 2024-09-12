package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * FindCommand class which encapsulates a Command which finds tasks.
 */
public class FindCommand extends Command {
    private String name;

    public FindCommand() {
        this.name = "";
    }

    public FindCommand(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        TaskList matchingTasks = taskList.findTasks(this.name);
        return ui.printTaskList(matchingTasks);
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }
}
