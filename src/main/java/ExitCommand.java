public class ExitCommand extends Command{

    public ExitCommand(String command) {
        super(command);
    }
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
