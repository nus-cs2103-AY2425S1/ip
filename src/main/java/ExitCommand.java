public class ExitCommand extends Command {
    public ExitCommand(String commandBody) {
        super(commandBody);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
