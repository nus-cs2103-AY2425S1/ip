package nayana.command;

import nayana.NayanaException;
import nayana.Storage;
import nayana.TaskList;
import nayana.Ui;
import nayana.task.Task;

/**
 * Represents a command to add a task.
 * This command adds a specified task to the task list, updates the storage,
 * and prints a message to the user interface.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes the add task command.
     * Adds the task to the task list, updates the storage, and prints a message.
     *
     * @param tasks   The task list to which the task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks.
     * @throws NayanaException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NayanaException {
        assert tasks != null;
        tasks.addTask(task); // Adds the task to the task list.
        assert storage != null;
        storage.writeToFile(tasks.getTasks()); // Updates storage with the new list of tasks.
        assert ui != null;
        ui.printAddTask(task, tasks.getSize()); // Displays a confirmation message with the current task list size.
    }

    /**
     * Determines if this command is an exit command.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns Task to add for testing purposes
     *
     * @return Task
     */
    public Task getTask() {
        return this.task;
    }
}
