public class IncorrectCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TickException {
        throw new TickException("I don't know what that means!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
