package noosy.commands;

import noosy.exception.NoosyException;
import noosy.storage.Storage;
import noosy.task.Task;
import noosy.task.TaskList;
import noosy.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * This command marks the "done" status of a task at a specific index in the task list.
 */
public class MarkCommand extends Command {

    /**
     * The index of the task in the task list to be marked.
     */
    private final int index;

    /**
     * Constructs an MarkCommand with the specified task index.
     *
     * @param index the index of the task to be marked in the task list
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task at the specified index.
     * It updates the task's status to "done", displays this information to the user,
     * and saves the updated task list.
     *
     * @param tasks the task list containing the task to be unmarked
     * @param ui the user interface to display feedback to the user
     * @param storage the storage system to save the updated task list
     * @throws NoosyException if the specified task index is out of bounds or unavailable
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoosyException {
        try {
            Task completed = tasks.get(this.index);
            completed.markDone();
            ui.showTaskDone(completed);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new NoosyException("This task number is unavailable at the moment. \n " +
                    "Please try again later.");
        }
    }
}