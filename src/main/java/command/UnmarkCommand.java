package command;

import java.io.IOException;

import task.TaskList;
import exception.ScheduloException;
import util.Storage;
import util.Ui;


/**
 * The UnmarkCommand class represents a command to mark a task as not done in the task list.
 * It extends the Command class and provides the implementation for unmarking tasks.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as not done in the task list.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by marking the task at the specified index as not done and saving the updated list to storage.
     *
     * @param tasks   The TaskList containing the task to be marked as not done.
     * @param ui      The Ui instance used to interact with the user.
     * @param storage The Storage instance used to save the updated task list.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ScheduloException, IOException {
        tasks.unmark(index - 1);
        storage.save(tasks);
    }
}
