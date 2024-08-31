package command;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EdithException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
