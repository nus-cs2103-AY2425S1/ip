public class MarkCommand extends Command {
    private int toMark;

    public MarkCommand(int index) {
        this.toMark = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        Task doneTask = tasks.markTaskAsDone(this.toMark);
        ui.showTaskMarked(doneTask);
        storage.saveData(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
