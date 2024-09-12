package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * UnmarkCommand class which encapsulates a Command which unmarks a task.
 */
public class UnmarkCommand extends CommandWithIndex {

    public UnmarkCommand() {
        super(-1);
    }

    public UnmarkCommand(int idx) {
        super(idx);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Task taskMarked = taskList.markTaskIncomplete(super.index);
        return ui.printTaskMarkedIncompleteMessage(taskMarked);
    }
}
