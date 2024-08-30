public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    public boolean isExit(){
        return false;
    }
}
