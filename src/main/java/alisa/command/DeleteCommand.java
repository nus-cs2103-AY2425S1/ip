package alisa.command;

import alisa.exception.AlisaException;
import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs an instance of DeleteCommand.
     *
     * @param index Index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes a task from the list of tasks.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     * @throws AlisaException If the index is out of bounds in taskList or the storage file can't be updated.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.deleteTask(index);
        storage.syncFile(taskList);
        return message;
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