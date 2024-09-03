public abstract class Command {
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage) throws ToothlessExceptions;
}
