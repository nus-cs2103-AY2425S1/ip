package duke.commands;

import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidPriorityException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a priority to a task in the task list in the DailyTasks application.
 * This command parses the user input, retrieves the task by its index, and sets the priority.
 */
public class SetPriorityCommand extends Command {

    /**
     * The index of the task to which the priority will be added.
     */
    private final int taskIndex;

    /**
     * The priority level to be added to the task.
     */
    private final int priority;

    /**
     * Constructs a new AddPriorityCommand with the specified task index and priority level.
     *
     * @param taskIndex The index of the task in the task list.
     * @param priority  The priority level to be assigned to the task.
     */
    public SetPriorityCommand(int taskIndex, int priority) throws InvalidPriorityException {
        if (priority < 0) {
            throw new InvalidPriorityException("priority must be > 0");
        }
        this.taskIndex = taskIndex;
        this.priority = priority;
    }

    /**
     * Executes the command by adding the specified priority to the task at the given index.
     * If the task index is out of bounds or the task cannot be found, an appropriate error message is displayed.
     *
     * @param taskList The list of tasks from which the task will be retrieved.
     * @param ui       The user interface used to display messages to the user.
     * @param storage  The storage system responsible for saving and loading tasks.
     * @return A message indicating the priority has been added to the task.
     * @throws InvalidInputException If the task index is invalid or if the task cannot accept the priority.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        assert taskList != null : "TaskList must not be null";
        assert ui != null : "UI must not be null";
        assert storage != null : "Storage must not be null";

        try {
            // Get the task at the specified index
            Task task = taskList.getTask(this.taskIndex);

            // Set the priority for the task
            task.setPriority(this.priority);

            // Notify the user that the priority has been added
            return ui.formatAddPriorityTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("The task index is out of range. Please enter a valid task index.");
        }
    }
}
