import java.io.IOException;

public abstract class CommandExecutor {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ArtsException, IOException;

    public boolean isExit() {
        return false;
    }
}
