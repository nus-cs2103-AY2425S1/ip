package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanMissingArgumentException;
import stan.tasks.Task;
import stan.ui.Ui;



/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand.
     *
     * @param words The user input split into words.
     * @throws StanMissingArgumentException If the task number is missing.
     * @throws StanInvalidArgumentException If the task number is invalid.
     */
    public DeleteCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2) {
            throw new StanMissingArgumentException("You need to specify the task number to delete.");
        }
        try {
            this.taskIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new StanInvalidArgumentException("The task number must be a valid integer.");
        }
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks The task list where the task will be deleted.
     * @param ui The UI object to display feedback to the user.
     * @param storage The storage object to save the updated task list.
     * @throws StanInvalidArgumentException If the task number is out of range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidArgumentException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new StanInvalidArgumentException("The task number is out of range.");
        }
        Task removedTask = tasks.remove(taskIndex);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskDeleted(removedTask, tasks.size());
    }
}
