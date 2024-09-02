public class AddCommand extends CommandBase {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException {
        tasks.addTask(task);  // This should now use the updated format
        storage.save(tasks.getAllTasks());
    }
}
