package alisa.command;

import alisa.AlisaException;
import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an instance of UnmarkCommand.
     *
     * @param index Index of the task in the list of tasks to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Marks the task as not done.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     * @throws AlisaException If the storage file can't be updated.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.unmarkTask(index);
        ui.showMessage(message);
        storage.syncFile(taskList);
    }

    /**
     * {@inheritDoc}
     *
     * Indicates that the program should not terminate.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
