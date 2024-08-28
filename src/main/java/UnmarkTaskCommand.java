public class UnmarkTaskCommand implements Command {
    private int index;

    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NuggetException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        ui.showUnmarkedTask(tasks.getTask(index));
    }
}
