package diego;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private int index;

    /**
     * Constructs a new UnmarkCommand.
     *
     * @param index The index of the task to be unmarked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark a task as not done in the task list.
     *
     * @param tasks   The task list containing the task to be unmarked.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.unMark();
        ui.showTaskUnmarked(task);
        storage.save(tasks);
    }
}
