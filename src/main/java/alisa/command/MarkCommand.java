package alisa.command;

import alisa.exception.AlisaException;
import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs an instance of MarkCommand.
     *
     * @param index Index of the task in the list of tasks to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Marks the task as complete.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     * @throws AlisaException If the storage file can't be updated.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.markTask(index);
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
