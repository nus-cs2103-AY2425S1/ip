package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.ui.Ui;

public class MarkCommand extends CommandWithIndex {

    public MarkCommand() {
        super(-1);
    }

    public MarkCommand(int idx) {
        super(idx);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Task taskMarked = taskList.markTaskCompleted(super.index);
        ui.printTaskAddedMessage(taskMarked, taskList.getTasks().size());
    }
}
