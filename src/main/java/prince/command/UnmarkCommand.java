package prince.command;

import prince.exceptions.PrinceException;
import prince.tasks.Task;
import prince.tasks.TaskList;
import prince.util.Storage;
import prince.util.Ui;

/**
 * Command that unmarks a task.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * A constructor for UnmarkCommand class.
     * @param input Input by the user
     */
    public UnmarkCommand(String input) {
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
     * Marks task as incomplete.
     * Update storage with correct boolean status.
     * Displays input for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     * @param ui Ui as initialised in main.
     * @throws PrinceException
     */
    private void unmark(String input, TaskList taskList, Storage storage, Ui ui) throws PrinceException {
        // extra check to make sure the start of input is "unmark"
        String checkUnmark = input.substring(0, 6);
        if (checkUnmark.equals("unmark")) {
            int index = getIndex(input);
            Task task = taskList.get(index);
            task.markAsNotDone();
            storage.updateFile(input, taskList);
            ui.showTaskToString(task);
        } else {
            throw new PrinceException("Please ensure that your input begins with 'unmark'!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        unmark(this.input, tasks, storage, ui);
    }
}
