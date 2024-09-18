package sirpotato;

/**
 * Class that handles the adding of a task to the tasklist
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns the task being added
     * 
     * @return The task being added
     */
    public Task getTask() {
        return task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        return ui.displayAddedTask(task, tasks);
    }
}