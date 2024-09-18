package lebron;

/**
 * Represents a command to delete a task from the task list. This command is
 * responsible for removing a task based on its index, displaying a deletion
 * message to the user interface, and saving the updated task list to storage.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be
     * deleted.
     *
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index
     * from the task list, displaying a message to the user interface, and
     * saving the updated task list to storage.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui The user interface that will display the task deletion message.
     * @param storage The storage where the updated task list is saved.
     * @throws LeBronException If the specified index is invalid or an error
     *     occurs during command execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        String msg = ui.showTaskDeleted(task);
        storage.saveTasks(taskList);
        return msg;
    }
}
