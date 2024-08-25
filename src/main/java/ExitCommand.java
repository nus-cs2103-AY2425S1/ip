public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitResponse();
    }

    @Override
    public Boolean isExit() {
        return true;
    }
}
