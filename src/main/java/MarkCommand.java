public class MarkCommand extends Command{
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OllieException {
        Task task = tasks.markAsDone(index);
        ui.showMarkAsDone(task);
    }
}
