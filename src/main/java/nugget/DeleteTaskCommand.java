package nugget;

public class DeleteTaskCommand implements Command {
    private int index;

    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showTaskRemoved(task, tasks.size());
    }
}
