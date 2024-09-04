package prince.command;

import prince.exceptions.PrinceException;
import prince.tasks.Task;
import prince.tasks.TaskList;
import prince.util.Storage;
import prince.util.Ui;

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
     * Returns an integer representing the index of a task in an array.
     * @param input Input by the user.
     * @return Index of the task.
     */
    private int getIndex(String input) {
        // get character value of index in the input
        String indexAsString = input.substring(7);
        // convert to arr index
        int index = Integer.valueOf(indexAsString) - 1;
        return index;

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
        if (checkDelete.equals("delete")) {
            // remove the task from storage.txt
            storage.deleteFromFile(input, taskList);
            int index = getIndex(input);
            Task task = taskList.get(index);
            taskList.remove(index);
            ui.showDelete();
            ui.showTaskToString(task);
            ui.showNumberOfTasks(taskList);
        } else {
            throw new PrinceException("Please ensure that your input begins with 'delete'!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        delete(input, tasks, storage, ui);
    }
}
