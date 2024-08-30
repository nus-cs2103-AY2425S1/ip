package thebotfather.command;

import thebotfather.task.Task;
import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * The task to be added to the task list.
     */
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command by adding the task to the task list, saving the updated task list to storage,
     * and printing the current task count.
     *
     * @param taskList The task list where the task will be added.
     * @param ui The user interface used to interact with the user.
     * @param storage The storage system used to save the task list.
     * @throws TheBotFatherException If an error occurs while saving the task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        taskList.addTask(task);
        storage.toFile(taskList);
        ui.printCount();
    }
}
