public class UnknownCommand extends Command{
    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MoniqueException {
        throw new UnknownCommandException();
    }
}
