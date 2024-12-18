package barry.command;

import barry.task.Task;
import barry.util.TaskList;
import barry.util.Ui;
import barry.util.Storage;
import java.io.IOException;

/**
 * Represents a command that will mark a task as undone in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param allTasks The list of all tasks containing the task to be marked undone.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system for saving updated task list.
     * @throws IOException If there is an error during input or output operations.
     */
    @Override
    public void execute(TaskList allTasks, Ui ui, Storage storage) throws IOException {
        try {
            Task task = allTasks.getTask(index);
            task.markAsUndone();
            ui.showUnmark(task);
            storage.save(allTasks.getAllTasks());
        } catch (IOException e) {
            ui.showError(" Oh no... This is an invalid task number!");;
        }
    }
}
