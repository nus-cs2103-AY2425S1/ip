public abstract class Command {

    public abstract void execute(TaskList list, Ui ui, Storage storage) throws JudeException;

}
