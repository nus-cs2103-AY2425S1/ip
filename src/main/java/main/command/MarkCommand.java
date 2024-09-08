package main.command;

import main.exceptions.PrinceException;
import main.tasks.Task;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Command that marks a task.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * A constructor for UnmarkCommand class.
     * @param input Input by the user
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks task as complete.
     * Update storage with correct boolean status.
     * Displays input for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     * @param ui Ui as initialised in main.
     * @throws PrinceException
     */
    private void mark(String input, TaskList taskList, Storage storage, Ui ui) throws PrinceException {
        if (input.equals("mark")) {
            throw new PrinceException("Don't forget to include the number of the task!");
        }
        String checkMark = input.substring(0, 4);
        if (!checkMark.equals("mark")) {
            throw new PrinceException("Please ensure that your input begins with 'mark'!");
        }
        int index = getIndex(input);
        Task task = taskList.get(index);
        task.markAsDone();
        storage.updateFile(input, taskList);
        ui.showMark();
        ui.showTaskToString(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        mark(this.input, tasks, storage, ui);
    }
}
