package lebron;

/**
 * This command is responsible for adding a task to the task list,
 * displaying a message to the user interface, and saving the updated
 * task list to storage.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * displaying a message to the user interface, and saving the updated
     * task list to storage.
     *
     * @param taskList The task list to which the task is added.
     * @param ui The user interface that will display the added task message.
     * @param storage The storage where the updated task list is saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveTasks(taskList);
    }
    
}
