package diego;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Constructs a new DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   The task list from which the task is deleted.
     * @param ui      The UI component that handles user interaction.
     * @param storage The storage component to save the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.remove(index);
        ui.showTaskDeleted(task, tasks.size());
        storage.save(tasks);
    }
}
