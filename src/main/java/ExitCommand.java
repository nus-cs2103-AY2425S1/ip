public class ExitCommand extends Command {

    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) {
        ui.showExitCommand();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
