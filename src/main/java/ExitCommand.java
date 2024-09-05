public class ExitCommand extends Command {
    public ExitCommand(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        ui.showGoodbye();

    }

}
