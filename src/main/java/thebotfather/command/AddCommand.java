package thebotfather.command;

import thebotfather.task.Task;
import thebotfather.util.Storage;
import thebotfather.util.TaskList;
import thebotfather.util.TheBotFatherException;
import thebotfather.util.Ui;

/**
 * A command that adds a new task to the task list.
 */
public class AddCommand extends Command {

    /** The task to be added to the task list. */
    private final Task task;

    /** A message to be printed to the user upon adding the task. */
    private final String toPrint;

    /**
     * Constructs an {@code AddCommand} with the specified task and confirmation message.
     *
     * @param task The task to be added to the task list.
     * @param toPrint The message to be displayed to the user upon adding the task.
     */
    public AddCommand(Task task, String toPrint) {
        this.task = task;
        this.toPrint = toPrint;
    }

    /**
     * Executes the command to add the task to the task list. It saves the updated list to storage and
     * provides a user-facing confirmation message.
     *
     * @param taskList The task list where the new task will be added.
     * @param ui The user interface for interaction with the user.
     * @param storage The storage system to save the task list.
     * @return A string that contains the user-facing confirmation message and the added task details.
     * @throws TheBotFatherException If an error occurs during saving to storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        taskList.addTask(task);
        storage.toFile(taskList);
        return this.toPrint + task.toString();
    }
}
