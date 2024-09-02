public class DeleteCommand extends CommandBase {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        try {
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.printTaskDeleted(task);
            storage.save(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new AlexException("Task not found.");
        }
    }
}