package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark or unmark a task as done in the DailyTasks application.
 * This command updates the status of the task based on the user's input.
 */
public class MarkTaskCommand extends Command {

    /** A flag indicating whether to mark the task as done or not done. */
    private boolean markAsDone;

    /** The index of the task to be marked or unmarked. */
    private int taskIndex;

    /**
     * Constructs a new MarkTaskCommand with the specified action (mark as done or not done) and task index.
     *
     * @param markAsDone True to mark the task as done, false to mark it as not done.
     * @param taskIndex The index of the task to be marked or unmarked.
     */
    public MarkTaskCommand(boolean markAsDone, int taskIndex) {
        super();
        this.markAsDone = markAsDone;
        this.taskIndex = taskIndex;
        // Assert that the taskIndex is not negative
        assert taskIndex >= 0 : "Task index must not be negative";
    }

    /**
     * Executes the command by marking or unmarking a task as done or not done.
     * The task status is updated and a message is displayed to confirm the change.
     *
     * @param taskList The list of tasks where the task is located.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage system responsible for saving and loading tasks (not used in this implementation).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        // Assert that taskList and ui are not null
        assert taskList != null : "Task list must not be null";
        assert ui != null : "UI object must not be null";

        // Ensure taskIndex is within valid bounds
        assert this.taskIndex >= 0 && this.taskIndex < taskList.getTasks().size() : "Task index is out of bounds";

        if (this.taskIndex != -1) {
            Task task = taskList.getTask(this.taskIndex);
            // Assert that the task is not null
            assert task != null : "Task should not be null";

            if (this.markAsDone) {
                task.setDone();
                return ui.formatMarkTask(task);
            } else {
                task.setNotDone();
                return ui.formatUnmarkTask(task);
            }
        }
        throw new InvalidInputException("Please enter a valid task index to mark!");
    }
}
