
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MajimaException {
        try {
            Task task = tasks.get(index);
            task.markAsUndone();
            ui.showTaskUnmarked(task);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new MajimaException("Task number is out of range.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
