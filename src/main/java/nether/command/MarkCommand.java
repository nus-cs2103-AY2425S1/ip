package nether.command;

import nether.NetherException;
import nether.Ui;
import nether.storage.Storage;
import nether.task.Task;
import nether.task.TaskList;

/**
 * Represents a command that marks a task as done or not done.
 * <p>
 * The {@code MarkCommand} class is an abstract class that defines the common functionality for all commands.
 * </p>
 */
public abstract class MarkCommand extends Command {
    protected int taskIndex;

    /**
     * Constructs a {@code MarkCommand} with the specified task number.
     *
     * @param taskNumber The index of the task to be marked.
     */
    public MarkCommand(int taskNumber) {
        this.taskIndex = taskNumber;
    }

    /**
     * Executes the mark command by marking the specified task and updating the task list, user interface,
     * and storage.
     * <p>
     * This method validates the task number, retrieves the task, marks it accordingly, prints a message
     * to the user, and saves the updated task list.
     * </p>
     *
     * @param tasks The {@code TaskList} instance containing the tasks.
     * @param ui The {@code Ui} instance used to interact with the user.
     * @param storage The {@code Storage} instance used to save the updated tasks.
     * @throws NetherException If the task number is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NetherException {
        if (taskIndex > tasks.getSize() || taskIndex < 1) {
            throw new NetherException("invalid task number!");
        }

        Task taskToMark = tasks.getTask(taskIndex - 1);
        // taskNumber needs to be decremented since list index starts from 0
        markTask(taskToMark);

        storage.saveTasks(tasks.getTasks());
        return ui.printMarkMessage(taskToMark, getMarkMessage());
    }

    /**
     * Marks the specified task as done or not done.
     * <p>
     * This method must be implemented by subclasses to define specific marking behavior.
     * </p>
     *
     * @param taskToMark The task to be marked.
     */
    public abstract void markTask(Task taskToMark);

    /**
     * Returns the message to be displayed to the user after marking a task.
     * <p>
     * This method must be implemented by subclasses to provide the appropriate marking message.
     * </p>
     *
     * @return The message indicating the result of the marking operation.
     */
    public abstract String getMarkMessage();
}
