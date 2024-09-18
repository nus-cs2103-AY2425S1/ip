package lebron;

/**
 * Represents a command to unmark a task as not done in the task list.
 * This command retrieves a task by its index, marks it as not done,
 * updates the storage, and returns a confirmation message.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index of the task to be marked as not done.
     *
     * @param index The index of the task in the task list to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by marking the task at the specified index as not done.
     * The task list is updated, the storage is saved, and a confirmation message is returned.
     *
     * @param taskList The list of tasks.
     * @param ui The UI object used to interact with the user.
     * @param storage The storage object used to save the updated task list.
     * @return A string message confirming that the task has been marked as not done.
     * @throws LeBronException If the task cannot be unmarked due to an invalid index or other error.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        Task task = taskList.getTask(index);
        taskList.unmarkTask(index);
        String msg = ui.showTaskUnmarked(task);
        storage.saveTasks(taskList);
        return msg;
    }
}
