package duke.commands;

import duke.exceptions.InvalidInputException;
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
        // Assert that taskIndex is not negative other than -1 (invalid index)
        assert taskIndex >= -1 : "Task index cannot be less than -1";
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        // Assert that taskList and ui are not null before proceeding
        assert taskList != null : "Task list must not be null";
        assert ui != null : "UI object must not be null";

        if (this.taskIndex != -1) {
            // Assert that the taskIndex is within the range of the task list
            assert this.taskIndex >= 0 && this.taskIndex < taskList.getTasks().size() : "Task index is out of bounds";

            try {
                Task task = taskList.deleteTask(this.taskIndex);
                return ui.formatDeleteTask(task, taskList.getTasks().size());
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidInputException("Sorry, there are only " + taskList.getTasks().size() + " tasks!");
            }
        }
        throw new InvalidInputException("Please enter a valid task index to delete!");
    }
}
