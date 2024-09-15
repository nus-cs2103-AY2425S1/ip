package taskon.commands;

import taskon.exception.TaskonException;
import taskon.storage.Storage;
import taskon.task.Event;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 * <p>
 * This class handles the creation and addition of an event task with a specified
 * description, start time, and end time. It updates the user interface to show the
 * added task and saves the updated task list to storage.
 * </p>
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private Task task;

    /**
     * Constructs an {@code EventCommand} with the specified event details.
     * <p>
     * This constructor initializes the command with a description, start time, and end time
     * for the event task.
     * </p>
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String description, String from, String to) throws TaskonException {
        this.task = new Event(description, from, to);
    }

    /**
     * Returns the event task associated with this command.
     *
     * @return The event task.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Executes the command to add an event task to the task list.
     * <p>
     * This method adds the event task to the task list, updates the user interface
     * to show the added task, and saves the updated task list to storage.
     * </p>
     *
     * @param taskList The list of tasks where the event will be added.
     * @param ui The user interface that handles the display of messages to the user.
     * @param storage The storage that handles saving the updated task list.
     * @return A string message that confirms the addition of the task to the task list, including
     *     the total number of tasks in the list after the addition.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return ui.showTaskAdded(task, taskList.size());
    }
}
