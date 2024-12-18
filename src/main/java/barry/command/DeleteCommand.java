package barry.command;

import barry.task.Task;
import barry.util.TaskList;
import barry.util.Ui;
import barry.util.Storage;

import java.io.IOException;

/**
 * Represents a command given by the user to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete task.
     * Deletes the task to the task list, displays addition to the user,
     * and saves the updated task to storage.
     *
     *
     * @param allTasks The list of all tasks on which the task will be deleted from.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for saving updated tasks.
     * @throws IOException If there is an error during input or output operations.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        try {
            Task removed = allTasks.removeTask(index);
            ui.showDeleteTask(removed, allTasks.size());
            storage.save(allTasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError(" Oh no... This is an invalid task number!");;
        }
    }
}
