package sammy.command;

import sammy.task.TaskList;
import sammy.Ui;
import sammy.SammyException;
import sammy.Storage;
import sammy.task.Task;
import sammy.exceptions.InvalidTaskNumberException;

import java.io.IOException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the task at the specified index as done,
     * displaying the confirmation to the user, and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui The UI object to interact with the user.
     * @param storage The storage object to save the task list.
     * @throws SammyException If the task index is invalid.
     * @throws IOException If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SammyException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.get(index);
        task.markAsDone();
        storage.save(tasks);
        return ui.showMarkTask(task);
    }
}

