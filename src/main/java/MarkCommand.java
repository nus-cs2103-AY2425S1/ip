public class MarkCommand extends Command {

    private final int index;

    MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMarkTask(tasks.markTask(this.index));
    }
}
