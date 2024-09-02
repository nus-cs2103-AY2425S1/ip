package taskon.commands;

import taskon.storage.Storage;
import taskon.task.Deadline;
import taskon.task.Task;
import taskon.task.TaskList;
import taskon.ui.Ui;

/**
 * Represents a command to add a deadline task.
 * <p>
 * This class handles the creation of a new deadline task and its addition to the task list.
 * It also updates the user interface to show that the task has been added and saves the
 * updated task list to storage.
 * </p>
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private Task task;

    /**
     * Constructs a {@code DeadlineCommand} with the specified description and date.
     * @param description The description of the deadline task.
     * @param date The date of when the task is due in the format specified by the application.
     */
    public DeadlineCommand(String description, String date) {
        this.task = new Deadline(description, date);
    }

    /**
     * Returns the {@code Task} associated with this command.
     * @return The {@code Task} to be added.
     */
    public Task getTask() {
        return task;
    }


    /**
     * Executes the command to add the deadline task to the task list.
     * <p>
     * This method adds the deadline task to the specified task list, updates the user interface
     * to show the added task, and saves the updated task list to storage.
     * </p>
     *
     * @param taskList The list of tasks managed by the application.
     * @param ui       The user interface that handles output and user interactions.
     * @param storage  The storage that handles data persistence.
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
