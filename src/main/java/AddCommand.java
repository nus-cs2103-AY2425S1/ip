/**
 * Represent the command to add a new task to the task list.
 * This command implements the Command interface and is for
 * adding a single task to the task list and displaying the confirmation message
 * that the task has been successfully added to the user.
 */
public class AddCommand implements Command {

    /** The task to be added to the task list */
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task object to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheritDoc
     *
     * Adds the task to the task list and displays a confirmation message.
     *
     * @param tasks The TaskList which the new task will be added.
     * @param ui The Ui object used to display the confirmation message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
        ui.displayAddedTask(task);
    }
}
