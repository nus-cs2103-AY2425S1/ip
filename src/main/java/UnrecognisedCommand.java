public class UnrecognisedCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Bro whatchu yapping! I don't know what are you talking about");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
