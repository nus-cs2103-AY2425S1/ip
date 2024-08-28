public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieException;

    public abstract boolean isExit();
}
