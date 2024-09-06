public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException;

    public abstract boolean isExit();

}
