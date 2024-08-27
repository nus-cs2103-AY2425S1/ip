public class DeleteCommand extends Command{
    private int index;

    /**
     * Creates a DeleteCommand with the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list and updates the storage file.
     *
     * @param tasks The task list from which the task is to be deleted.
     * @param ui The user interface to interact with the user.
     * @param storage The storage file to be updated.
     * @throws DudeException If there is an error deleting the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DudeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DudeException("There is no such task!");
        }
        Task task = tasks.deleteTask(index);
        storage.saveTasks(tasks);
        ui.showDeleted(task, tasks);
    }

    /**
     * Returns false because this is not an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
