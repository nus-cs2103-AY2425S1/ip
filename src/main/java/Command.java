import java.io.IOException;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DookException, IOException;

    public boolean isExit() {
        return this.isExit;
    }
}
