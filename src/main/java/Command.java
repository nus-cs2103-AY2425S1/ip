public abstract class Command {
    abstract void execute (TaskList tasks, Ui ui, Storage storage) throws NextGPTException;
    abstract boolean isExit();
}
