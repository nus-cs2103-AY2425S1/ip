package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list in the DailyTasks application.
 * <p>
 * This command deletes the task at the specified index and displays a message confirming the deletion.
 * If the task index is invalid, no task will be deleted, and no message will be shown.
 * </p>
 */
public class DeleteTaskCommand extends Command {

    /** The index of the task to be deleted. */
    private final int taskIndex;

    /**
     * Constructs a new DeleteTaskCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted. A value of -1 indicates an invalid index.
     */
    public DeleteTaskCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by deleting a task from the task list.
     * <p>
     * If the task index is valid (i.e., not -1), the task is removed and a message confirming the deletion
     * is displayed to the user. If the index is invalid, no action is taken.
     * </p>
     *
     * @param taskList The {@link TaskList} from which the task will be deleted.
     * @param ui The {@link Ui} used to display messages to the user.
     * @param storage The {@link Storage} system responsible for saving and
     *                loading tasks (not used in this implementation).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskIndex != -1) {
            Task task = taskList.deleteTask(this.taskIndex);
            ui.showMessage(ui.formatDeleteTask(task, taskList.getTasks().size()));
        }
    }
}
