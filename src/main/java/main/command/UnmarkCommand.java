package main.command;

import main.exceptions.PrinceException;
import main.tasks.Task;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

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
        if (input.equals("unmark")) {
            throw new PrinceException("Don't forget to include the number of the task!");
        }
        String checkUnmark = input.substring(0, 6);
        if (checkUnmark.equals("unmark")) {
            throw new PrinceException("Please ensure that your input begins with 'unmark'!");
        }
        int index = getIndex(input);
        Task task = taskList.get(index);
        task.markAsNotDone();
        storage.updateFile(input, taskList);
        ui.showUnmark();
        ui.showTaskToString(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        unmark(this.input, tasks, storage, ui);
    }
}
