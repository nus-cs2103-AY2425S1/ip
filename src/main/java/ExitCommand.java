public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
