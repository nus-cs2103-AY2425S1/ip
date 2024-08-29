public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) throws AiException {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
