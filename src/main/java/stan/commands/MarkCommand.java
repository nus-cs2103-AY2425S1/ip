package stan.commands;

import stan.Storage;
import stan.TaskList;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanMissingArgumentException;
import stan.tasks.Task;
import stan.ui.Ui;




/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand.
     *
     * @param words The user input split into words.
     * @throws StanMissingArgumentException If the task number is missing.
     * @throws StanInvalidArgumentException If the task number is invalid.
     */
    public MarkCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2) {
            throw new StanMissingArgumentException("You need to specify the task number to mark.");
        }
        try {
            this.taskIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new StanInvalidArgumentException("The task number must be a valid integer.");
        }
    }

    /**
     * Executes the command to mark a task as done in the task list.
     *
     * @param tasks The task list containing the task to be marked.
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
        task.markAsDone();
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskMarked(task, tasks.size());
    }
}
