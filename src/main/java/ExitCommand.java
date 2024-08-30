public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BarcusException {
        ui.showGoodbye();
        storage.upload(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
