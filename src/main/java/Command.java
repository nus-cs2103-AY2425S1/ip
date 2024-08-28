

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException;

    public boolean isExit() {
        return false;
    }
    
}
