public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LexiException {
        super.setExit();
        ui.showBye();
    }
}
