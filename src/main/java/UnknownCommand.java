public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ui.showError("command not found D:");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
