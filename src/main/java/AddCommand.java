public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        tasks.addTask(task);
        storage.updateStorage(tasks.getTasks());
        int taskSize = tasks.getSize();
        ui.showAddMessage(task, taskSize);
    }
}
