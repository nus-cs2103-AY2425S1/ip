public class ExitCommand extends Command {
    private boolean isExit;

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        this.isExit = false;
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
