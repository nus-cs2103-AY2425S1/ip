package topaz.command;

import java.io.IOException;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.task.Task;
import topaz.ui.Ui;

/**
 * Represents a command to delete a task from the taskList in the system.
 * The task to be deleted is identified by its index in the TaskList.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified keyword and index.
     *
     * @param keyword The keyword specifying the type of command (e.g., "delete").
     * @param index   The index of the task to be deleted (1-based index).
     */
    public DeleteCommand(String keyword, int index) {
        super(keyword);
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the TaskList.
     * The task is removed from the TaskList, the changes are saved using Storage,
     * and a confirmation message is displayed to the user via the Ui.
     *
     * @param tasks   The TaskList from which the task will be deleted.
     * @param ui      The Ui used to display messages to the user.
     * @param storage The Storage used to save the updated task list to file after deletion completed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.removeTask(index - 1);
            storage.save(tasks);
            return ui.showDeleteTask(task, tasks.getSize());
        } catch (IOException e) {
            return ui.showSaveIoeException(e);
        } catch (IndexOutOfBoundsException e) {
            return ui.showException(e);
        }
    }
}
