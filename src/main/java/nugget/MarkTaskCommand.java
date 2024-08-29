package nugget;

public class MarkTaskCommand implements Command {
    private int index;

    public MarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showMarkedTask(tasks.getTask(index));
    }
}
