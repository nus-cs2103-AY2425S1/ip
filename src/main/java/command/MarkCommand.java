package command;

import java.io.IOException;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done and saves the updated task list to the storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            storage.save(tasks);
            return ui.showTaskMarked(task);
        } catch (IndexOutOfBoundsException | IOException e) {
            return ui.showSavingError();
        }
    }
}
