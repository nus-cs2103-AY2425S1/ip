public abstract class Command {
    private Storage storage;
    private TaskList tasks;

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException;
}
