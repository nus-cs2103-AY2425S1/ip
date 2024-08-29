abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TudeeException;
    public boolean isExit() {
        return false;
    }
}
