public abstract class Command {

    public abstract void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException;
    public abstract boolean isExit();

}
