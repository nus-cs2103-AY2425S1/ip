public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AstraException;

    public abstract boolean isExit();
}
