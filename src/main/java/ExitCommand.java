public class ExitCommand implements Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No special actions needed for exit
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
