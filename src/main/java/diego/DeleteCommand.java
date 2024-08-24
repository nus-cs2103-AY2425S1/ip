package diego;
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.remove(index);
        ui.showTaskDeleted(task, tasks.size());
        storage.save(tasks);
    }
}