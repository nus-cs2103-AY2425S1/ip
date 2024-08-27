public class ByeCommand implements Command {
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.displayExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
