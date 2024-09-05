package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * DeleteCommand class encapsulating a Command to Delete a Task.
 */
public class DeleteCommand extends CommandWithIndex {

    public DeleteCommand() {
        super(-1);
    }

    public DeleteCommand(int idx) {
        super(idx);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Task taskDeleted = taskList.deleteTask(super.index);
        return ui.printTaskDeletedMessage(taskDeleted, taskList.getTasks().size());
    }
}
