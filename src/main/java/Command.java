public abstract class Command {
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException;

    public boolean isExit() {
        return false;
    }
}
