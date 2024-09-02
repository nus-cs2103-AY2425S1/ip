package alisa.command;

import alisa.*;
import alisa.task.Task;
import alisa.task.TaskList;

public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an instance of AddCommand.
     *
     * @param task Task to add into the list of tasks.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     *
     * Adds a task to the taskList.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     * @throws AlisaException If the storage file can't be updated.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlisaException {
        String message = taskList.addTask(task);
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
