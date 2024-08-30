abstract public class Command {

    protected boolean isExit = false;
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws LightException;


    public boolean isExit() {

        return isExit;
    }
}
