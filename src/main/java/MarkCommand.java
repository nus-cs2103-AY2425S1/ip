public class MarkCommand extends Command{
    private boolean isDone;
    private int index;

    public MarkCommand(int n, boolean isDone) {
        this.isDone = isDone;
        this.index = n;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
