package main.command;

import main.exceptions.PrinceException;
import main.tasks.Task;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command that deletes task from the task list.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructor for DeleteCommand class.
     * @param input Input by the user.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes tasks from taskList and storage.
     * Displays input for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     * @param ui Ui as initialised in main.
     * @throws PrinceException
     */
    private void delete(String input, TaskList taskList, Storage storage, Ui ui) throws PrinceException {
        // extra check to make sure the start of input is "delete"
        String checkDelete = input.substring(0, 6);
        if (!checkDelete.equals("delete")) {
            throw new PrinceException("Please ensure that your input begins with 'delete'!");
        }
        storage.deleteFromFile(input, taskList);
        int index = getIndex(input);
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.showDelete();
        ui.showTaskToString(task);
        ui.showNumberOfTasks(taskList);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        delete(input, tasks, storage, ui);
    }
}
