public class ExitCommand extends Command{
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException {
        ui.close();
    }

    @Override
    protected boolean isExit() {
        return true;
    }
}
