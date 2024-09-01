public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        ui.showLine();
        ui.showMessage("Goodbye. We shall meet again soon.");
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
