public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
    }
    @Override
    public boolean isBye() {
        return true;
    }
}
