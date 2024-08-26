package simon;
/**
 * Represents a command to mark or unmark a task in the task list.
 * Implements the Command interface to define the execution behavior for updating task status.
 */
public class MarkCommand implements Command{
    int index;
    boolean mark;
    /**
     * Constructs a MarkCommand with the specified index and mark status.
     *
     * @param mark true to mark the task as completed, false to unmark it
     * @param index the index of the task in the task list to be marked or unmarked
     */
    public MarkCommand(boolean mark, int index) {

        this.index = index;
        this.mark = mark;
    }
    /**
     * Executes the command to mark or unmark a task in the task list.
     * The status of the task is updated, feedback is shown to the user, and the updated task list is saved to storage.
     *
     * @param taskList the list of tasks in which the task's status will be updated
     * @param ui the user interface used to show feedback to the user
     * @param storage the storage used to save the updated task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(mark, index);
        if(mark) {
            ui.showTaskMarked(taskList.get(index));
        }
        else{
            ui.showTaskUnmarked(taskList.get(index));
        }
        storage.saveToFile(taskList.toArr());

    }
}
