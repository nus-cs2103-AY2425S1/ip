public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
