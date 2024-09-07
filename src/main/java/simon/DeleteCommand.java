package simon;
/**
 * Represents a command to delete a task from the task list.
 * Implements the Command interface to define the execution behavior for deleting tasks.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index of the task to be deleted.
     *
     * @param index the index of the task in the task list to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }
    public int getIndex() {
        return this.index;
    }

    /**
     * Executes the command to delete a task from the task list.
     * The task is removed from the task list, the change is shown to the user,
     * and the updated task list is saved to storage.
     *
     * @param taskList the list of tasks from which the task will be deleted
     * @param ui the user interface used to show feedback to the user
     * @param storage the storage used to save the updated task list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        Task task = taskList.pop(index);
        String s = ui.showTaskDeleted(task, taskList.size());
        storage.saveToFile(taskList.toArr());
        return s;

    }
}
