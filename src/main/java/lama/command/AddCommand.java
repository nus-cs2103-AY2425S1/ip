package lama.command;


import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;
import lama.task.Task;

/**
 * Represent a command to add a task to the task list.
 * Subclass of command.
 */

public class AddCommand extends Command {

    private Task task;

    /**
     * Construct an AddCommand with specified task given.
     *
     * @param task Task to be added into the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Run the command by adding the specified task to the task list.
     * This method also update the storage and reflect user interface.
     *
     * @param taskList Task list to which the task will be added.
     * @param storage Storage used to save the task.
     * @param ui User interface that interacts with user.
     * @throws LamaException Thrown if an error occurs during the execution of the command.
     */
    @Override
    public String run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        assert taskList != null : "Task list should not be null";
        assert storage != null : "Storage should not be null";
        assert ui != null : "UI should not be null";

        taskList.add(task);
        ui.showAddCommand(taskList);
        storage.addTask(task);
        String output = "Got it. I've added this task:\n"
                + "  " + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return output;
    }
}
