package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.ui.Ui;

public class UnmarkCommand extends CommandWithIndex {

    public UnmarkCommand() {
        super(-1);
    }

    public UnmarkCommand(int idx) {
        super(idx);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Task taskMarked = taskList.markTaskIncomplete(super.index);
        ui.printTaskDeletedMessage(taskMarked, taskList.getTasks().size());
    }
}
