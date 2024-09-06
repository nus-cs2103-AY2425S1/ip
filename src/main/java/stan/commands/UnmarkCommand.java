package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanMissingArgumentException;
import stan.tasks.Task;
import stan.ui.Ui;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param words The user input split into words.
     * @throws StanMissingArgumentException If the task number is missing.
     * @throws StanInvalidArgumentException If the task number is invalid.
     */
    public UnmarkCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2) {
            throw new StanMissingArgumentException("You need to specify the task number to unmark.");
        }
        try {
            this.taskIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new StanInvalidArgumentException("The task number must be a valid integer.");
        }
    }

    /**
     * Executes the command to unmark a task as done in the task list.
     *
     * @param tasks The task list containing the task to be unmarked.
     * @param ui The UI object to display feedback to the user.
     * @param storage The storage object to save the updated task list.
     * @throws StanInvalidArgumentException If the task number is out of range.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidArgumentException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new StanInvalidArgumentException("The task number is out of range.");
        }
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskUnmarked(task, tasks.size());
    }
}
