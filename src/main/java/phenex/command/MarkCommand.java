package phenex.command;

import phenex.exception.PhenexException;
import phenex.storage.Storage;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.ui.Ui;

/**
 * MarkCommand class which encapsulates a Command which Marks a task.
 */
public class MarkCommand extends CommandWithIndex {
    private double hoursTaken;

    public MarkCommand() {
        super(0);
    }

    public void setHoursTaken(double hoursTaken) {
        this.hoursTaken = hoursTaken;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws PhenexException {
        Task taskMarked = taskList.markTaskCompleted(super.index, hoursTaken);
        return ui.printTaskMarkedCompleteMessage(taskMarked);
    }
}
