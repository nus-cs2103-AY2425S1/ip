public abstract class Command {
    public abstract void execute(TaskList taskLists, Ui ui, Storage storage);

    public abstract boolean isBye();
}
