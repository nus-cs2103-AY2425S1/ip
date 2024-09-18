package lebron;

/**
 * Represents a command to mark a task as completed in the task list. This
 * command retrieves a task by its index, marks it as completed, updates the
 * storage, and returns a confirmation message.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be
     * marked as completed.
     *
     * @param index The index of the task in the task list to be marked as
     *     completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the task at the specified index as
     * completed. The task list is updated, the storage is saved, and a
     * confirmation message is returned.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object used to save the updated task list.
     * @return A string message confirming that the task has been marked as
     *     completed.
     * @throws LeBronException If the task cannot be marked due to an invalid
     *     index or other error.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.markTask(index);
        String msg = ui.showTaskMarked(task);
        storage.saveTasks(taskList);
        return msg;
    }
}
